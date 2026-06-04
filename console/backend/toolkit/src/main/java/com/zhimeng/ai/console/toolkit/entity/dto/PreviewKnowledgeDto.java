package com.zhimeng.ai.console.toolkit.entity.dto;

import com.zhimeng.ai.console.toolkit.entity.mongo.PreviewKnowledge;
import com.zhimeng.ai.console.toolkit.entity.table.repo.FileInfoV2;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PreviewKnowledgeDto extends PreviewKnowledge {
    private FileInfoV2 fileInfoV2;
}
