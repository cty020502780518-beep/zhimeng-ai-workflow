package com.zhimeng.ai.console.toolkit.entity.dto;

import com.zhimeng.ai.console.toolkit.entity.table.repo.UploadDocTask;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UploadDocTaskDto extends UploadDocTask {
    private String sourceId;
}
