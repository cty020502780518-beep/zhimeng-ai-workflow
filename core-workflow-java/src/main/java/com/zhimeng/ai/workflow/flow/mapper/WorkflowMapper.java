package com.zhimeng.ai.workflow.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhimeng.ai.workflow.flow.entity.WorkflowEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Workflow mapper
 */
@Mapper
public interface WorkflowMapper extends BaseMapper<WorkflowEntity> {
}
