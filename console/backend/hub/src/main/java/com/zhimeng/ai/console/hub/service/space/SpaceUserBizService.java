package com.zhimeng.ai.console.hub.service.space;

import com.zhimeng.ai.console.commons.response.ApiResult;
import com.zhimeng.ai.console.commons.dto.space.UserLimitVO;

public interface SpaceUserBizService {

    ApiResult<String> enterpriseAdd(String uid, Integer role);

    ApiResult<String> remove(String uid);

    ApiResult<String> updateRole(String uid, Integer role);

    ApiResult<String> quitSpace();

    ApiResult<String> transferSpace(String uid);

    UserLimitVO getUserLimit();

    UserLimitVO getUserLimit(String uid);

    UserLimitVO getUserLimitVO(Integer type, String uid);
}
