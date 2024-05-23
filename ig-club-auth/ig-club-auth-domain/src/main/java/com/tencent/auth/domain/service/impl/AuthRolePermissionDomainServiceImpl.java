package com.tencent.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.tencent.auth.common.enums.IsDeletedFlagEnum;
import com.tencent.auth.domain.convert.AuthPermissionBOConverter;
import com.tencent.auth.domain.convert.AuthRolePermissionBOConverter;
import com.tencent.auth.domain.entity.AuthPermissionBO;
import com.tencent.auth.domain.entity.AuthRolePermissionBO;
import com.tencent.auth.domain.service.AuthPermissionDomainService;
import com.tencent.auth.domain.service.AuthRolePermissionDomainService;
import com.tencent.auth.infra.basic.entity.AuthPermission;
import com.tencent.auth.infra.basic.entity.AuthRolePermission;
import com.tencent.auth.infra.basic.service.AuthPermissionService;
import com.tencent.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;


    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            authRolePermissionService.insert(authRolePermission);

        });

    /*    AuthRolePermission authRolePermission = AuthRolePermissionBOConverter.INSTANCE.convertBOToEntity(authRolePermissionBO);
        if (log.isInfoEnabled()) {
            log.info("authRolePermission entity before insertion: {}", JSON.toJSONString(authRolePermission));
        }*/
        return true;

    }

}
