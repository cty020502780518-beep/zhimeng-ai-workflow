package com.zhimeng.ai.console.hub.service.chat;

public interface ChatReqRespService {

    void updateBotChatContext(Long chatId, String uid, Integer botId);
}
