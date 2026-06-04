package com.zhimeng.ai.console.commons.service.space;

import com.zhimeng.ai.console.commons.entity.space.EnterprisePermission;

import java.util.Collection;
import java.util.List;

/**
 * Enterprise team role permission configuration
 */
public interface EnterprisePermissionService {

    EnterprisePermission getEnterprisePermissionByKey(String key);

    List<String> listByKeys(Collection<String> keys);

    void insertBatch(List<EnterprisePermission> enterprisePermissions);

}
