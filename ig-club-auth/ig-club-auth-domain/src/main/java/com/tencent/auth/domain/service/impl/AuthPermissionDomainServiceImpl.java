package com.tencent.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.tencent.auth.common.enums.IsDeletedFlagEnum;
import com.tencent.auth.domain.convert.AuthPermissionBOConverter;
import com.tencent.auth.domain.convert.AuthRoleBOConverter;
import com.tencent.auth.domain.entity.AuthPermissionBO;
import com.tencent.auth.domain.entity.AuthRoleBO;
import com.tencent.auth.domain.service.AuthPermissionDomainService;
import com.tencent.auth.domain.service.AuthRoleDomainService;
import com.tencent.auth.infra.basic.entity.AuthPermission;
import com.tencent.auth.infra.basic.entity.AuthRole;
import com.tencent.auth.infra.basic.service.AuthPermissionService;
import com.tencent.auth.infra.basic.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;


    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        if (log.isInfoEnabled()) {
            log.info("authPermission entity before insertion: {}", JSON.toJSONString(authPermission));
        }
        Integer count = authPermissionService.insert(authPermission);
        if (log.isInfoEnabled()) {
            log.info("Database insertion result: {} rows affected", count);
        }
        return count>0;

    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        Integer count = authPermissionService.update(authPermission);
        return count>0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authPermissionService.update(authPermission);
        return count>0;
    }
}
