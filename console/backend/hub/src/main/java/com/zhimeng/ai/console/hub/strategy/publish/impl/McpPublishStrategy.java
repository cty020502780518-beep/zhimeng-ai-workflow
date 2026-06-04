package com.zhimeng.ai.console.hub.strategy.publish.impl;

import com.zhimeng.ai.console.commons.enums.bot.ReleaseTypeEnum;
import com.zhimeng.ai.console.commons.constant.ResponseEnum;
import com.zhimeng.ai.console.commons.exception.BusinessException;
import com.zhimeng.ai.console.commons.response.ApiResult;
import com.zhimeng.ai.console.hub.strategy.publish.PublishStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MCP publish strategy implementation Handles bot publishing to MCP server channel
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class McpPublishStrategy implements PublishStrategy {

    @Override
    public ApiResult<Object> publish(Integer botId, Object publishData, String currentUid, Long spaceId) {
        log.info("MCP publishing request: botId={}", botId);
        // TODO: Implement MCP publishing logic
        throw new BusinessException(ResponseEnum.SYSTEM_ERROR, "MCP publishing not implemented");
    }

    @Override
    public ApiResult<Object> offline(Integer botId, Object publishData, String currentUid, Long spaceId) {
        log.info("MCP offline request: botId={}", botId);
        // TODO: Implement MCP offline logic
        throw new BusinessException(ResponseEnum.SYSTEM_ERROR, "MCP offline not implemented");
    }

    @Override
    public String getPublishType() {
        return ReleaseTypeEnum.MCP.name();
    }
}
