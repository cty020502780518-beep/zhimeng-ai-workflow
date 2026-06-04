package com.zhimeng.ai.console.toolkit.entity.dto;

import com.zhimeng.ai.console.toolkit.entity.biz.workflow.BizWorkflowData;
import lombok.Data;

@Data
public class WorkflowComparisonReq {

    Long id;

    String flowId;

    BizWorkflowData data;

    String version;

    String name;

    Integer type;

    String promptId;
}
