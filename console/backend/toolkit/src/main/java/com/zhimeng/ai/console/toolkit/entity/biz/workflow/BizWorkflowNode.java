package com.zhimeng.ai.console.toolkit.entity.biz.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhimeng.ai.console.toolkit.entity.biz.workflow.node.BizNodeData;
import lombok.Data;

@Data
public class BizWorkflowNode {
    String id;
    String flowId;
    @Deprecated
    String name;
    Boolean dragging;
    Boolean selected;
    String icon;
    Integer width;
    Integer height;
    Object position;
    Object positionAbsolute;
    String type;
    BizNodeData data;
    @JsonProperty("zIndex")
    Object zIndex;
    String parentId;
    String extent;
    @JsonProperty("draggable")
    Object draggable;
}
