package com.tencent.auth.domain.service.impl;

import com.tencent.auth.common.enums.AuthUserStatusEnum;
import com.tencent.auth.common.enums.IsDeletedFlagEnum;
import com.tencent.auth.domain.convert.AuthUserBOConverter;
import com.tencent.auth.domain.entity.AuthUserBO;
import com.tencent.auth.domain.service.AuthUserDomainService;
import com.tencent.auth.infra.basic.entity.AuthUser;
import com.tencent.auth.infra.basic.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;


    @Override
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        return count>0;
    }
}
