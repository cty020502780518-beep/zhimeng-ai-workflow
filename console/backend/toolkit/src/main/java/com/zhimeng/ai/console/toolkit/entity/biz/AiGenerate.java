package com.zhimeng.ai.console.toolkit.entity.biz;

import lombok.Data;

@Data
public class AiGenerate {
    Long botId;
    Long flowId;
    String code;
    String prompt;
}
