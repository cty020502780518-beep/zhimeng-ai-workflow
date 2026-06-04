package com.zhimeng.ai.console.toolkit.entity.spark.request;

import com.zhimeng.ai.console.toolkit.entity.spark.Text;
import lombok.Data;

import java.util.List;

@Data
public class Message {
    List<Text> text;
}
