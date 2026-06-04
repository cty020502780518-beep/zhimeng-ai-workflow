package com.zhimeng.ai.console.toolkit.entity.core.workflow.ws;

import com.alibaba.fastjson2.JSONObject;
import com.zhimeng.ai.console.toolkit.entity.biz.workflow.ChatInputHistory;
import lombok.Data;

import java.util.List;

@Data
public class ChatInput {
    JSONObject inputs;
    List<ChatInputHistory> history;
    String uid;
    String appId;
}
