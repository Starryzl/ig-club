package com.tencent.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.alibaba.fastjson.JSON;
import com.tencent.auth.common.enums.AuthUserStatusEnum;
import com.tencent.auth.common.enums.IsDeletedFlagEnum;
import com.tencent.auth.domain.convert.AuthUserBOConverter;
import com.tencent.auth.domain.entity.AuthUserBO;
import com.tencent.auth.domain.service.AuthUserDomainService;
import com.tencent.auth.infra.basic.entity.AuthUser;
import com.tencent.auth.infra.basic.service.AuthUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    private String salt = "ig";
    @Override
    @SneakyThrows
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
        if (log.isInfoEnabled()) {
            log.info("AuthUser entity before insertion: {}", JSON.toJSONString(authUser));
        }
        Integer count = authUserService.insert(authUser);
        if (log.isInfoEnabled()) {
            log.info("Database insertion result: {} rows affected", count);
        }
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
