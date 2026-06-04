package com.zhimeng.ai.console.toolkit.entity.vo.database;

import lombok.Data;

import java.util.List;

@Data
public class DbTableInfoVo {

    private String Label;

    private String value;

    private List<DbTableInfoVo> children;
}
