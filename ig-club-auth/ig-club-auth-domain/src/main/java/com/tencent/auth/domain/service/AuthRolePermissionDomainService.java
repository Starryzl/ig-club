package com.tencent.auth.domain.service;

import com.tencent.auth.domain.entity.AuthPermissionBO;
import com.tencent.auth.domain.entity.AuthRolePermissionBO;

/**
 * 角色领域service
 */
public interface AuthRolePermissionDomainService {

    Boolean add(AuthRolePermissionBO authRolePermissionBO);

}
