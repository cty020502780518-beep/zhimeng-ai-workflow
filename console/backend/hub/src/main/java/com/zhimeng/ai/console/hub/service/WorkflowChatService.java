package com.zhimeng.ai.console.hub.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zhimeng.ai.console.commons.dto.workflow.WorkflowChatRequest;
import com.zhimeng.ai.console.commons.dto.workflow.WorkflowEventData;
import com.zhimeng.ai.console.commons.dto.workflow.WorkflowResumeReq;
import com.zhimeng.ai.console.commons.entity.chat.ChatReqRecords;
import com.zhimeng.ai.console.commons.util.SseEmitterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.BufferedSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkflowChatService {

    @Value("${workflow.engine.url:http://localhost:7880}")
    private String workflowEngineUrl;

    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(5, java.util.concurrent.TimeUnit.MINUTES)
            .build();

    /**
     * Create SSE stream for workflow chat. Forwards request to the Java workflow
     * engine and relays the SSE stream back to the frontend.
     */
    public SseEmitter workflowChatStream(WorkflowChatRequest request) {
        SseEmitter emitter = SseEmitterUtil.createSseEmitter();
        String streamId = request.getChatId() + "_" + request.getUserId() + "_" + System.currentTimeMillis();
        workflowChatStream(request, emitter, streamId, null, false);
        return emitter;
    }

    public void workflowChatStream(WorkflowChatRequest request, SseEmitter emitter, String streamId,
            ChatReqRecords chatReqRecords, boolean edit) {
        if (chatReqRecords == null || chatReqRecords.getUid() == null || chatReqRecords.getChatId() == null) {
            SseEmitterUtil.completeWithError(emitter, "Chat records are empty");
            return;
        }

        try {
            Map<String, Object> body = buildRequestBody(request);
            String json = JSON.toJSONString(body);

            RequestBody reqBody = RequestBody.create(json, MediaType.parse("application/json"));
            Request httpReq = new Request.Builder()
                    .url(workflowEngineUrl + "/api/v1/workflow/chat/stream")
                    .post(reqBody)
                    .header("Content-Type", "application/json")
                    .build();

            httpClient.newCall(httpReq).enqueue(new WorkflowCallback(emitter, streamId, chatReqRecords, edit));

        } catch (Exception e) {
            log.error("Failed to create workflow chat stream, streamId: {}", streamId, e);
            SseEmitterUtil.completeWithError(emitter, "Failed to create workflow stream: " + e.getMessage());
        }
    }

    public SseEmitter resumeWorkflow(WorkflowResumeReq request) {
        SseEmitter emitter = SseEmitterUtil.createSseEmitter();
        String streamId = request.getChatId() + "_resume_" + System.currentTimeMillis();

        try {
            Map<String, Object> body = new HashMap<>();
            body.put("eventId", request.getEventId());
            body.put("eventType", request.getEventType());
            body.put("content", request.getContent());

            String json = JSON.toJSONString(body);
            RequestBody reqBody = RequestBody.create(json, MediaType.parse("application/json"));
            Request httpReq = new Request.Builder()
                    .url(workflowEngineUrl + "/api/v1/workflow/chat/resume")
                    .post(reqBody)
                    .header("Content-Type", "application/json")
                    .build();

            httpClient.newCall(httpReq).enqueue(new WorkflowCallback(emitter, streamId, null, false));
        } catch (Exception e) {
            log.error("Failed to resume workflow, streamId: {}", streamId, e);
            SseEmitterUtil.completeWithError(emitter, "Failed to resume workflow: " + e.getMessage());
        }

        return emitter;
    }

    private Map<String, Object> buildRequestBody(WorkflowChatRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("flowId", request.getFlowId());
        body.put("uid", request.getUserId());
        body.put("chatId", request.getChatId());
        body.put("stream", request.getStream() != null ? request.getStream() : true);
        body.put("messages", request.getMessages());
        body.put("parameters", request.getParameters());
        body.put("ext", request.getExt());
        return body;
    }

    /**
     * HTTP SSE callback handler — relays workflow engine SSE events to frontend.
     */
    private class WorkflowCallback implements Callback {
        private final SseEmitter emitter;
        private final String streamId;
        private final ChatReqRecords chatReqRecords;
        private final boolean edit;

        WorkflowCallback(SseEmitter emitter, String streamId, ChatReqRecords chatReqRecords, boolean edit) {
            this.emitter = emitter;
            this.streamId = streamId;
            this.chatReqRecords = chatReqRecords;
            this.edit = edit;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            log.error("Workflow connection failed, streamId: {}, error: {}", streamId, e.getMessage());
            SseEmitterUtil.completeWithError(emitter, "Connection failed: " + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (!response.isSuccessful()) {
                log.error("Workflow request failed, streamId: {}, status: {}", streamId, response.code());
                SseEmitterUtil.completeWithError(emitter, "Request failed: " + response.message());
                return;
            }

            try (ResponseBody body = response.body()) {
                if (body != null) {
                    relaySSEStream(body, emitter, streamId, chatReqRecords, edit);
                } else {
                    SseEmitterUtil.completeWithError(emitter, "Response body is empty");
                }
            }
        }
    }

    private void relaySSEStream(ResponseBody body, SseEmitter emitter, String streamId,
            ChatReqRecords chatReqRecords, boolean edit) {
        BufferedSource source = body.source();
        StringBuilder finalResult = new StringBuilder();

        try {
            while (true) {
                if (SseEmitterUtil.isStreamStopped(streamId)) {
                    log.info("Stream stopped by client, streamId: {}", streamId);
                    sendFinalEvent(emitter, finalResult, "workflow_interrupted");
                    break;
                }

                String line = source.readUtf8Line();
                if (line == null) break;

                if (line.startsWith("data:")) {
                    if (line.contains("[DONE]")) {
                        sendFinalEvent(emitter, finalResult, "workflow_complete");
                        break;
                    }

                    String data = line.substring(5).trim();
                    parseAndForwardSSEData(data, emitter, streamId, finalResult);
                }
            }
        } catch (IOException e) {
            log.error("Error reading workflow SSE stream, streamId: {}", streamId, e);
            sendFinalEvent(emitter, finalResult, "workflow_interrupted");
        }
    }

    private void parseAndForwardSSEData(String data, SseEmitter emitter, String streamId,
            StringBuilder finalResult) {
        try {
            JSONObject dataObj = JSON.parseObject(data);

            // Check for workflow event_data (indicates stream end)
            if (dataObj.containsKey("event_data")) {
                sendFinalEvent(emitter, finalResult, "workflow_event_data_close");
                return;
            }

            // Forward to frontend
            try {
                emitter.send(SseEmitter.event().name("data").data(data));
            } catch (Exception e) {
                log.debug("Client disconnected, streamId: {}", streamId);
            }

            // Accumulate result text
            if (dataObj.containsKey("choices")) {
                var choices = dataObj.getJSONArray("choices");
                if (choices != null && !choices.isEmpty()) {
                    var choice = choices.getJSONObject(0);
                    var delta = choice.getJSONObject("delta");
                    if (delta != null && delta.containsKey("content")) {
                        finalResult.append(delta.getString("content"));
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Failed to parse workflow SSE data, streamId: {}", streamId, e);
        }
    }

    private void sendFinalEvent(SseEmitter emitter, StringBuilder finalResult, String type) {
        JSONObject result = new JSONObject();
        result.put("type", type);
        result.put("finalResult", finalResult.toString());
        result.put("timestamp", System.currentTimeMillis());
        SseEmitterUtil.sendComplete(emitter, result);
        SseEmitterUtil.sendEndAndComplete(emitter);
    }
}
