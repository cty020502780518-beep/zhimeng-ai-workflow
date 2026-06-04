package com.zhimeng.ai.console.toolkit.entity.spark;

import com.zhimeng.ai.console.toolkit.entity.spark.request.FcFunction;
import com.zhimeng.ai.console.toolkit.entity.spark.request.Message;
import com.zhimeng.ai.console.toolkit.entity.spark.response.Choices;
import com.zhimeng.ai.console.toolkit.entity.spark.response.Usage;
import lombok.Data;

@Data
public class Payload {
    // request
    Message message;

    // response
    Choices choices;
    Usage usage;

    FcFunction functions;
}
