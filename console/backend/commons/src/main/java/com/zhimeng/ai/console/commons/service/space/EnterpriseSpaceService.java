package com.zhimeng.ai.console.commons.service.space;

import com.zhimeng.ai.console.commons.entity.space.EnterprisePermission;
import com.zhimeng.ai.console.commons.entity.space.EnterpriseUser;
import com.zhimeng.ai.console.commons.entity.space.SpacePermission;
import com.zhimeng.ai.console.commons.entity.space.SpaceUser;

public interface EnterpriseSpaceService {

    String getUidByCurrentSpaceId(Long spaceId);

    SpaceUser checkUserBelongSpace(Long spaceId, String uid);

    void clearSpaceUserCache(Long spaceId, String uid);

    EnterpriseUser checkUserBelongEnterprise(Long enterpriseId, String uid);

    void clearEnterpriseUserCache(Long enterpriseId, String uid);

    EnterprisePermission getEnterprisePermissionByKey(String key);

    SpacePermission getSpacePermissionByKey(String key);

    boolean checkEnterpriseExpired(Long enterpriseId);

    boolean checkSpaceExpired(Long spaceId);

}
