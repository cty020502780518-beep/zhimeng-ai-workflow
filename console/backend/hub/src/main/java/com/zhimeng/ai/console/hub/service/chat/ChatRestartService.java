package com.zhimeng.ai.console.hub.service.chat;


import com.zhimeng.ai.console.commons.dto.chat.ChatListCreateResponse;

public interface ChatRestartService {
    ChatListCreateResponse createNewTreeIndexByRootChatId(Long chatId, String uid, String chatListName);
}
