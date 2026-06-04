package com.zhimeng.ai.console.toolkit.entity.core.knowledge;

import lombok.Data;

@Data
public class KnowledgeResponse {
    Integer code;
    String sid;
    String message;
    Object data;
}
