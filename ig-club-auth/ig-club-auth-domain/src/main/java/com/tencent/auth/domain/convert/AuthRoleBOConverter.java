package com.tencent.auth.domain.convert;

import com.tencent.auth.domain.entity.AuthRoleBO;
import com.tencent.auth.domain.entity.AuthUserBO;
import com.tencent.auth.infra.basic.entity.AuthRole;
import com.tencent.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色bo转换器
 */
@Mapper
public interface AuthRoleBOConverter {
    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);

    AuthRole convertBOToEntity(AuthRoleBO authRoleBO);

}
