package com.zhimeng.ai.console.hub.service.space;

import com.zhimeng.ai.console.commons.response.ApiResult;
import com.zhimeng.ai.console.commons.dto.space.SpaceAddDTO;
import com.zhimeng.ai.console.commons.dto.space.SpaceUpdateDTO;
import com.zhimeng.ai.console.commons.entity.space.Space;

public interface SpaceBizService {

    ApiResult<Long> create(SpaceAddDTO spaceAddDTO, Long enterpriseId);

    ApiResult<String> deleteSpace(Long spaceId, String mobile, String verifyCode);

    ApiResult<String> updateSpace(SpaceUpdateDTO spaceUpdateDTO);

    ApiResult<Space> visitSpace(Long spaceId);

    ApiResult<String> sendMessageCode(Long spaceId);

    ApiResult<Boolean> ossVersionUserUpgrade();
}
