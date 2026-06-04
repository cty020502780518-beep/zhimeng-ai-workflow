package com.zhimeng.ai.console.toolkit.entity.core.workflow;


import com.zhimeng.ai.console.toolkit.entity.core.workflow.node.NodeData;
import lombok.Data;

@Data
public class Node {
    String id;
    NodeData data;
}
