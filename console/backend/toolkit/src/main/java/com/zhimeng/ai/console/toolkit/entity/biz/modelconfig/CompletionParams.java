package com.zhimeng.ai.console.toolkit.entity.biz.modelconfig;

import lombok.Data;

@Data
public class CompletionParams {
    Integer maxTokens;
    Double temperature;
    Integer topK;
    String auditing;
    String domain;
}
