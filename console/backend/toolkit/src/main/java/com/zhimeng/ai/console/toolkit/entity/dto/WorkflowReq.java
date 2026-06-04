package com.zhimeng.ai.console.toolkit.entity.dto;

import com.alibaba.fastjson2.JSONObject;
import com.zhimeng.ai.console.commons.enums.bot.BotTypeEnum;
import com.zhimeng.ai.console.toolkit.entity.biz.workflow.BizWorkflowData;
import com.zhimeng.ai.console.toolkit.entity.dto.talkagent.TalkAgentConfigDto;
import lombok.Data;

import java.util.Map;

@Data
public class WorkflowReq {
    Long id;
    String name;
    String description;
    BizWorkflowData data;
    Integer status;

    String appId;
    String avatarIcon;
    String avatarColor;

    String domain;
    Boolean commonUser;

    Integer source;
    String sourceCode;
    /**
     * Advanced configuration
     */
    Map<String, Object> advancedConfig;
    JSONObject ext;
    Integer category;
    String flowId;
    Long spaceId;
    Integer flowType = BotTypeEnum.WORKFLOW_BOT.getType();
    /**
     * Voice intelligent agent configuration
     */
    TalkAgentConfigDto flowConfig;
}
