package com.zhimeng.ai.console.toolkit.entity.core.openapi;

import lombok.Data;

import java.util.Map;

@Data
public class Response {
    Map<String, MediaType> content;
    String description = "success";

}
