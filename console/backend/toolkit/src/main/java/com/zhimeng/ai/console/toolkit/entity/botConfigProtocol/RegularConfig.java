package com.zhimeng.ai.console.toolkit.entity.botConfigProtocol;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegularConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    Rag rag = new Rag();
    Match match = new Match();
}
