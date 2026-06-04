package com.zhimeng.ai.console.hub.service.space;

import com.zhimeng.ai.console.commons.response.ApiResult;
import com.zhimeng.ai.console.commons.dto.space.UserLimitVO;

public interface EnterpriseUserBizService {

    ApiResult<String> remove(String uid);

    ApiResult<String> updateRole(String uid, Integer role);

    ApiResult<String> quitEnterprise();

    UserLimitVO getUserLimit(Long enterpriseId);
}
