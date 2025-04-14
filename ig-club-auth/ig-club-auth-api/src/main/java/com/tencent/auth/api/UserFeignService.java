package com.tencent.auth.api;

import com.tencent.auth.entity.AuthUserDTO;
import com.tencent.auth.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户服务Feign
 */
@FeignClient("ig-club-auth-dev")
public interface UserFeignService {

    @RequestMapping("/user/getUserInfo")
    Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);
}
