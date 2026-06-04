package com.zhimeng.ai.console.toolkit.entity.core.openapi;


import lombok.Data;

@Data
public class Parameter {
    String name;
    String in;
    String description;
    Boolean required;
    Schema schema;
}
