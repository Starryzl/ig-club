package com.tencent.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.alibaba.fastjson.JSON;
import com.tencent.auth.common.enums.AuthUserStatusEnum;
import com.tencent.auth.common.enums.IsDeletedFlagEnum;
import com.tencent.auth.domain.constants.AuthConstant;
import com.tencent.auth.domain.convert.AuthUserBOConverter;
import com.tencent.auth.domain.entity.AuthUserBO;
import com.tencent.auth.domain.service.AuthUserDomainService;
import com.tencent.auth.infra.basic.entity.AuthRole;
import com.tencent.auth.infra.basic.entity.AuthUser;
import com.tencent.auth.infra.basic.entity.AuthUserRole;
import com.tencent.auth.infra.basic.service.AuthRoleService;
import com.tencent.auth.infra.basic.service.AuthUserRoleService;
import com.tencent.auth.infra.basic.service.AuthUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthRoleService authRoleService;

    private String salt = "ig";

    private TransactionTemplate transactionTemplate;

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);

/*        HashMap<String, String> keyMap = SaSecureUtil.rsaGenerateKeyPair();
        String privateKey = keyMap.get("private");
        String publicKey = keyMap.get("public");
        authUser.setPassword(SaSecureUtil.rsaEncryptByPublic(publicKey,authUser.getPassword()));
        SaSecureUtil.rsaDecryptByPrivate(privateKey,authUser.getPassword());*/

        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(),salt));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        if(log.isInfoEnabled()){
            log.info("authUser:{}",JSON.toJSONString(authUser));
        }
        Integer count = authUserService.insert(authUser);

        //建立初步的角色关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        if(log.isInfoEnabled()){
            log.info("authRole:{}",JSON.toJSONString(authRole));
        }
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        if(log.isInfoEnabled()){
            log.info("roleResult:{}",JSON.toJSONString(roleResult));
        }
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);

 /*       //建立一个初步的角色的关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);*/
        return count>0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        Integer count = authUserService.update(authUser);
        return count>0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        return count>0;
    }


}
