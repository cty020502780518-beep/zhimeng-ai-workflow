package com.zhimeng.ai.console.hub.service.publish;

import com.zhimeng.ai.console.hub.dto.publish.mcp.McpPublishRequestDto;

/**
 * MCP Service Interface
 *
 * @author Omuigix
 */
public interface McpService {


    /**
     * Publish bot to MCP (corresponds to original interface: publishMCP)
     *
     * @param request Publish request
     * @param currentUid Current user ID
     * @param spaceId Space ID
     */
    void publishMcp(McpPublishRequestDto request, String currentUid, Long spaceId);
}
