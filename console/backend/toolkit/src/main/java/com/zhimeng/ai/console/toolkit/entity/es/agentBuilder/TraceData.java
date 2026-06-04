package com.zhimeng.ai.console.toolkit.entity.es.agentBuilder;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class TraceData {
    String llm_output;
    JSONObject input;
    TraceDataConfig config;
    Object output;
    Object usage;
}
