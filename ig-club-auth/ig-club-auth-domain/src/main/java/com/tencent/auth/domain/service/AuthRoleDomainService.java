package com.tencent.auth.domain.service;

import com.tencent.auth.domain.entity.AuthRoleBO;
import com.tencent.auth.domain.entity.AuthUserBO;

/**
 * 角色领域service
 */
public interface AuthRoleDomainService {

    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);
}
