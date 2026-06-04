package com.zhimeng.ai.console.commons.service.data;

import com.zhimeng.ai.console.commons.dto.dataset.DatasetStats;

import java.util.List;

public interface IDatasetFileService {

    List<DatasetStats> getMaasDataset(Long datasetId);

}
