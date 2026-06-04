package com.zhimeng.ai.console.toolkit.entity.dto;

import com.zhimeng.ai.console.toolkit.entity.table.repo.FileDirectoryTree;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileDirectoryTreeDto extends FileDirectoryTree {
    private static final long serialVersionUID = 1L;
    private List<TagDto> tagDtoList;
    // private Long hitCount;
}
