package com.tencent.auth.domain.convert;

import com.tencent.auth.domain.entity.AuthPermissionBO;
import com.tencent.auth.domain.entity.AuthRolePermissionBO;
import com.tencent.auth.infra.basic.entity.AuthPermission;
import com.tencent.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色bo转换器
 */
@Mapper
public interface AuthRolePermissionBOConverter {
    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);

    AuthRolePermission convertBOToEntity(AuthRolePermissionBO authRolePermissionBO);

}
