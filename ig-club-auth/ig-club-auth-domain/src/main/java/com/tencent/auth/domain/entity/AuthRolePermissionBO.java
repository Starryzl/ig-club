package com.tencent.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2024-05-23 08:37:48
 */
@Data
public class AuthRolePermissionBO implements Serializable {
    private static final long serialVersionUID = 464350651396417802L;
    
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;

    private List<Long> permissionIdList;
}

