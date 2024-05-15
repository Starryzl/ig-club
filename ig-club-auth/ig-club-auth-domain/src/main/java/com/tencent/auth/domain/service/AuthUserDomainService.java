package com.tencent.auth.domain.service;

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
}
