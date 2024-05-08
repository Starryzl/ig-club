package com.tencent.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        // 1、直接与数据库交互
        // 2、redis缓存
        // 3、redis缓存，没有再调用微服务获取
        List<String> permissionList = new LinkedList<>();
        permissionList.add("user:add");
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        List<String> permissionList = new LinkedList<>();
        permissionList.add("admin");
        return permissionList;
    }

}
