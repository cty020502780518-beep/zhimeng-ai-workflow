package com.zhimeng.ai.console.toolkit.entity.spark.chat;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecord {
    String role;
    String content;
}
