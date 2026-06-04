package com.zhimeng.ai.console.toolkit.entity.core.workflow;

import lombok.Data;

import java.util.List;

@Data
public class FlowProtocolData {
    List<Node> nodes;
    List<Edge> edges;
}
