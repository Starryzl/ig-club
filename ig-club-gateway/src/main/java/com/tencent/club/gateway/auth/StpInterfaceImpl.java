package com.tencent.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.tencent.club.gateway.redis.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;



import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        // 1、直接与数据库交互
        // 2、redis缓存
        // 3、redis缓存，没有再调用微服务获取
        return getAuth(loginId.toString(),authPermissionPrefix);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getAuth(loginId.toString(),authRolePrefix);
    }

    private List<String> getAuth(String loginId,String prefix){
        String authkey = redisUtil.buildKey(prefix, loginId.toString());
        String authValue = redisUtil.get(authkey);
        if(StringUtils.isBlank(authValue)){
            return Collections.emptyList();
        }
        List<String> authList  = new Gson().fromJson(authValue, List.class);
        return authList;
    }

}
