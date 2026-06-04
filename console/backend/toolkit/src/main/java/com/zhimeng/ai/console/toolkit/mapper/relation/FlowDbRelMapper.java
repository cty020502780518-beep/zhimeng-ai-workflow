package com.zhimeng.ai.console.toolkit.mapper.relation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhimeng.ai.console.toolkit.entity.dto.database.FlowDbRelCountDto;
import com.zhimeng.ai.console.toolkit.entity.table.relation.FlowDbRel;

import java.util.List;

public interface FlowDbRelMapper extends BaseMapper<FlowDbRel> {


    List<FlowDbRelCountDto> selectCountsByDbIds(List<Long> dbIds);

    void insertBatch(List<FlowDbRel> dbRelList);
}
