package com.zhimeng.ai.console.commons.service.data;

import com.zhimeng.ai.console.commons.dto.llm.SparkChatRequest;
import com.zhimeng.ai.console.commons.dto.chat.ChatModelMeta;
import com.zhimeng.ai.console.commons.dto.chat.ChatReqModelDto;
import com.zhimeng.ai.console.commons.dto.chat.ChatRequestDtoList;

import java.util.List;

/**
 * Chat history service interface
 */
public interface ChatHistoryService {

    /**
     * Get conversation history for system assistant
     *
     * @param uid User ID
     * @param chatId Chat ID
     * @return Message list
     */
    List<SparkChatRequest.MessageDto> getSystemBotHistory(String uid, Long chatId, Boolean supportDocument);

    /**
     * Get chat history records
     *
     * @param uid User ID
     * @param chatId Chat ID
     * @param reqList Request list
     * @return Chat request list
     */
    ChatRequestDtoList getHistory(String uid, Long chatId, List<ChatReqModelDto> reqList);

    /**
     * Convert URL to large model multimodal protocol content array
     *
     * @param url
     * @param ask
     * @return
     */
    List<ChatModelMeta> urlToArray(String url, String ask);
}
