package com.zhimeng.ai.console.hub.service.homepage;

import com.zhimeng.ai.console.hub.dto.homepage.BotListPageDto;
import com.zhimeng.ai.console.hub.dto.homepage.BotTypeDto;

import java.util.List;

/**
 * @author yun-zhi-ztl Agent Square service interface
 */
public interface AgentSquareService {
    List<BotTypeDto> getBotTypeList();

    BotListPageDto getBotPageByType(Integer type, String search, Integer pageSize, Integer page);
}
