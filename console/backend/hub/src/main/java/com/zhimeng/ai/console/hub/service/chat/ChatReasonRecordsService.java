package com.zhimeng.ai.console.hub.service.chat;

import com.zhimeng.ai.console.commons.dto.chat.ChatRespModelDto;
import com.zhimeng.ai.console.commons.entity.chat.ChatReasonRecords;
import com.zhimeng.ai.console.commons.entity.chat.ChatTraceSource;

import java.util.List;

public interface ChatReasonRecordsService {

    void assembleRespReasoning(List<ChatRespModelDto> respList, List<ChatReasonRecords> reasonRecordsList, List<ChatTraceSource> traceList);
}
