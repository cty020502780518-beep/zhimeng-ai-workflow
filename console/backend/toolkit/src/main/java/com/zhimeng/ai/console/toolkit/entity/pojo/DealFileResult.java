package com.zhimeng.ai.console.toolkit.entity.pojo;

import lombok.Data;

@Data
public class DealFileResult {
    private boolean parseSuccess;
    private String taskId;
    private String errMsg;
    private Integer failedCount;
}
