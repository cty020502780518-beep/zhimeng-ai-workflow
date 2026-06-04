package com.zhimeng.ai.console.toolkit.mapper.workflow;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhimeng.ai.console.toolkit.entity.table.workflow.WorkflowVersion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WorkflowVersionMapper extends BaseMapper<WorkflowVersion> {
    Page<WorkflowVersion> selectPageByCondition(Page<WorkflowVersion> page, @Param("flowId") String flowId);

    Page<WorkflowVersion> selectPageLatestByName(Page<?> page, @Param("botId") String botId);

    Long countLatestByName(@Param("botId") String botId);
}
