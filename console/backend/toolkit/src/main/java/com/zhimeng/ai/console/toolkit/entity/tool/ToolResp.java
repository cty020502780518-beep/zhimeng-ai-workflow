package com.zhimeng.ai.console.toolkit.entity.tool;

import lombok.Data;

@Data
public class ToolResp {
    Integer code;
    String message;
    String sid;
    Object data;
}
