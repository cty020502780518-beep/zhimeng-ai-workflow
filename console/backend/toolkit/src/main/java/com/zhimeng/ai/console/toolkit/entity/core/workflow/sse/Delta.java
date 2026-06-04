package com.zhimeng.ai.console.toolkit.entity.core.workflow.sse;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Delta {
    String role;
    String content;

    @JsonProperty("reasoning_content")
    String reasoningContent;
}
