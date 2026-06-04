package com.zhimeng.ai.console.toolkit.service.openapi;

import com.alibaba.fastjson2.JSONObject;
import com.zhimeng.ai.console.toolkit.entity.dto.openapi.WorkflowIoTransRequest;

import java.util.List;

/**
 * Open API Service Interface
 */
public interface OpenApiService {

    /**
     * Get workflow IO transformations by API key
     *
     * @param request Request containing API key and secret
     * @return Workflow IO transformation data
     */
    List<JSONObject> getWorkflowIoTransformations(WorkflowIoTransRequest request);
}
