package com.zhimeng.ai.console.hub.client;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Generic OpenAI-compatible LLM API client.
 * Supports DeepSeek, OpenAI, and any provider using the standard chat completions API.
 */
@Slf4j
@Component
public class LlmApiClient {

    @Value("${llm.api.url:https://api.deepseek.com/v1}")
    private String apiUrl;

    @Value("${llm.api.key:}")
    private String apiKey;

    @Value("${llm.api.model:deepseek-chat}")
    private String model;

    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build();

    /**
     * Streaming chat completion via SSE. Returns the buffered source for reading.
     */
    public Response streamChat(List<Map<String, String>> messages, Map<String, Object> options) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("model", model);
        body.put("messages", messages);
        body.put("stream", true);
        body.put("temperature", options.getOrDefault("temperature", 0.3));
        body.put("max_tokens", options.getOrDefault("max_tokens", 2000));

        String json = JSON.toJSONString(body);
        Request request = new Request.Builder()
                .url(apiUrl + "/chat/completions")
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .build();

        try {
            return httpClient.newCall(request).execute();
        } catch (IOException e) {
            log.error("LLM API call failed", e);
            throw new RuntimeException("LLM API error: " + e.getMessage(), e);
        }
    }

    /**
     * Non-streaming chat completion.
     */
    public String chat(List<Map<String, String>> messages) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("model", model);
        body.put("messages", messages);
        body.put("stream", false);

        String json = JSON.toJSONString(body);
        Request request = new Request.Builder()
                .url(apiUrl + "/chat/completions")
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.body() != null) {
                String respBody = response.body().string();
                JSONObject obj = JSON.parseObject(respBody);
                return obj.getJSONArray("choices").getJSONObject(0)
                        .getJSONObject("message").getString("content");
            }
            return "";
        } catch (IOException e) {
            log.error("LLM chat call failed", e);
            throw new RuntimeException("LLM chat error: " + e.getMessage(), e);
        }
    }
}
