package com.zhimeng.ai.console.hub.service.chat;

import com.zhimeng.ai.console.commons.dto.chat.ChatRespModelDto;
import com.zhimeng.ai.console.commons.entity.chat.ChatTraceSource;

import java.util.List;

public interface TraceToSourceService {

    void respAddTrace(List<ChatRespModelDto> respList, List<ChatTraceSource> traceList);
}
