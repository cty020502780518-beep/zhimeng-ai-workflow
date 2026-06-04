package com.zhimeng.ai.console.toolkit.entity.dto.eval;

import com.zhimeng.ai.console.toolkit.entity.table.trace.NodeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NodeDataDto extends NodeInfo {
    String markData;
}
