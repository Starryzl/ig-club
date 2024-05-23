package com.tencent.auth.application.convert;

import com.tencent.auth.application.dto.AuthPermissionDTO;
import com.tencent.auth.application.dto.AuthRolePermissionDTO;
import com.tencent.auth.domain.entity.AuthPermissionBO;
import com.tencent.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限关联dto转换器
 */
@Mapper
public interface AuthRolePermissionDTOConverter {
    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    AuthRolePermissionBO convertDTOToBO(AuthRolePermissionDTO authRolePermissionDTO);

}
