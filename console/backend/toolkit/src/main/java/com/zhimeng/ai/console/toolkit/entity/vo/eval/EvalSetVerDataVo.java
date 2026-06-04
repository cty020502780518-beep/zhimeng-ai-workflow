package com.zhimeng.ai.console.toolkit.entity.vo.eval;

import lombok.Data;

import java.util.Date;

@Data
public class EvalSetVerDataVo {
    Long id;
    Long evalSetVerId;
    Integer seq;
    String question;
    String answer;
    String sid;
    Date createTime;
}
