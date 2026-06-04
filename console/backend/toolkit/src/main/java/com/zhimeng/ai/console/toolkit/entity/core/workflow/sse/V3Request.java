package com.zhimeng.ai.console.toolkit.entity.core.workflow.sse;

import lombok.Data;

@Data
public class V3Request {

    String model;

    Object messages;

    boolean stream;

    String domain;


}
