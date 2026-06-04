package com.zhimeng.ai.console.hub.service.chat;

import com.zhimeng.ai.console.commons.dto.bot.ChatBotApi;

import java.util.List;

public interface ChatBotApiService {

    List<ChatBotApi> getBotApiList(String uid);

    boolean exists(Long botId);

    Long selectCount(Integer botId);

    void insertOrUpdate(ChatBotApi chatBotApi);

    ChatBotApi getOneByUidAndBotId(String uid, Long botId);
}
