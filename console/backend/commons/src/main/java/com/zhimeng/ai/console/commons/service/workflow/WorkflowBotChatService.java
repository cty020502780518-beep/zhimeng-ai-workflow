package com.zhimeng.ai.console.commons.service.workflow;

import com.zhimeng.ai.console.commons.dto.bot.ChatBotReqDto;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface WorkflowBotChatService {

    void chatWorkflowBot(ChatBotReqDto chatBotReqDto, SseEmitter sseEmitter, String sseId, String workflowOperation, String workflowVersion);
}
