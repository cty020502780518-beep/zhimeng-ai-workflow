package com.zhimeng.ai.console.hub.service.space;


import com.zhimeng.ai.console.commons.response.ApiResult;

public interface ApplyRecordBizService {

    ApiResult<String> joinEnterpriseSpace(Long spaceId);

    ApiResult<String> agreeEnterpriseSpace(Long applyId);

    ApiResult<String> refuseEnterpriseSpace(Long applyId);
}
