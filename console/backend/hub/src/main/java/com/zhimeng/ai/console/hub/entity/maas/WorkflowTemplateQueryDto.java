package com.zhimeng.ai.console.hub.entity.maas;

import lombok.Data;

@Data
public class WorkflowTemplateQueryDto {

    private int pageIndex = 1;

    private int pageSize = 15;

    private Integer groupId;
}
