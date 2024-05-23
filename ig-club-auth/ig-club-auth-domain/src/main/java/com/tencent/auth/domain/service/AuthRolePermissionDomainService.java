package com.tencent.auth.domain.service;

import com.tencent.auth.domain.entity.AuthPermissionBO;
import com.tencent.auth.domain.entity.AuthRolePermissionBO;
import com.tencent.auth.infra.basic.entity.AuthRolePermission;

import java.util.List;

/**
 * 角色领域service
 */
public interface AuthRolePermissionDomainService {

    Boolean add(AuthRolePermissionBO authRolePermissionBO);

}
