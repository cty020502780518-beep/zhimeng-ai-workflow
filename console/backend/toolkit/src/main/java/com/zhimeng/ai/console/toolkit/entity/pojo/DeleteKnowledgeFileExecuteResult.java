package com.zhimeng.ai.console.toolkit.entity.pojo;

import lombok.Data;

@Data
public class DeleteKnowledgeFileExecuteResult {
    private String sourceId;
    private Integer executeResult;
    private String failedReason;
}
