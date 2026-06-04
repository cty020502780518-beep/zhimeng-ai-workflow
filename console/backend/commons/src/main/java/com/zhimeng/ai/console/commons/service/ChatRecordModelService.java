package com.zhimeng.ai.console.commons.service;

import com.zhimeng.ai.console.commons.entity.chat.ChatReqRecords;

public interface ChatRecordModelService {

    void saveThinkingResult(ChatReqRecords chatReqRecords, StringBuffer thinkingResult, boolean edit);

    void saveChatResponse(ChatReqRecords chatReqRecords, StringBuffer finalResult, StringBuffer sid, boolean edit, Integer answerType);

}
