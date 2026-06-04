package com.zhimeng.ai.console.toolkit.entity.core.knowledge;

import lombok.Data;

import java.util.List;

@Data
public class QueryRespData {
    String query;
    Integer count;
    List<ChunkInfo> results;
}
