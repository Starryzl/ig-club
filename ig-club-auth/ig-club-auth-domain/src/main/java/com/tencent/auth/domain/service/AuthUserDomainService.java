package com.tencent.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.tencent.auth.domain.entity.AuthUserBO;

/**
 * 用户领域service
 */
public interface AuthUserDomainService {

    /**
     * 注册
     * @param authUserBO
     * @return
     */
    Boolean register(AuthUserBO authUserBO);

    /**
     * 更新用户信息
     * @param authUserBO
     * @return
     */
    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);

    SaTokenInfo doLogin(String validCode);
}
