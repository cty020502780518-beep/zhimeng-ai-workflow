package com.zhimeng.ai.console.toolkit.controller.open;

import com.alibaba.fastjson2.JSONObject;
import com.zhimeng.ai.console.commons.constant.ResponseEnum;
import com.zhimeng.ai.console.commons.response.ApiResult;
import com.zhimeng.ai.console.toolkit.common.anno.ResponseResultBody;
import com.zhimeng.ai.console.toolkit.entity.dto.openapi.WorkflowIoTransRequest;
import com.zhimeng.ai.console.toolkit.service.openapi.OpenApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Open API Controller for external service integration
 */
@RestController
@RequestMapping("/open-api")
@Slf4j
@ResponseResultBody
@Tag(name = "Open API interface")
public class OpenApiController {

    @Autowired
    private OpenApiService openApiService;

    private static final String AUTHORIZATION_PREFIX = "Bearer ";

    /**
     * Get workflow IO transformation data by API key
     *
     * @param authorization Authorization header in format "Bearer apiKey:apiSecret"
     * @return List of IO transformation data
     */
    @GetMapping("/workflow-io-info-list")
    @Operation(summary = "Get workflow IO transformations",
            description = "Retrieve workflow IO transformation data using API key authentication")
    public ApiResult<List<JSONObject>> getWorkflowIoInfoList(
            @RequestHeader("authorization") String authorization) {

        // Parse authorization header
        if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")) {
            return ApiResult.error(ResponseEnum.UNAUTHORIZED);
        }

        String credentials = authorization.substring(AUTHORIZATION_PREFIX.length());
        String[] parts = credentials.split(":");
        if (parts.length != 2) {
            return ApiResult.error(ResponseEnum.UNAUTHORIZED);
        }

        // Build request DTO
        WorkflowIoTransRequest request = new WorkflowIoTransRequest();
        request.setApiKey(parts[0]);
        request.setApiSecret(parts[1]);

        // Call service layer
        List<JSONObject> result = openApiService.getWorkflowIoTransformations(request);

        return ApiResult.success(result);
    }
}
