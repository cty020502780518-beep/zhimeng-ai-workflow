package com.zhimeng.ai.console.toolkit.entity.vo.repo;

import lombok.Data;

import java.util.List;

@Data
public class FileStatusVO {
    private String app_id;
    private String repo_id;
    private List<String> file_ids;
}
