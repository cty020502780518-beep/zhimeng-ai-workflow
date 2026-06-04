package com.zhimeng.ai.console.commons.mapper.dataset;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhimeng.ai.console.commons.entity.dataset.BotDatasetMaas;
import com.zhimeng.ai.console.commons.dto.dataset.DatasetStats;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BotDatasetMaasMapper extends BaseMapper<BotDatasetMaas> {

    List<DatasetStats> selectBotStatsMaps(@Param("datasetIds") List<Long> datasetIds);
}
