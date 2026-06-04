package com.zhimeng.ai.console.toolkit.entity.vo.group;

import lombok.Data;

import java.util.List;

@Data
public class DeleteGroupUserVO {
    private Long tagId;
    private List<String> uids;
}
