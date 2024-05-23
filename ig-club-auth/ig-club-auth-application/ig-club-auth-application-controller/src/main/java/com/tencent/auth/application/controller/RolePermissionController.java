package com.tencent.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.tencent.auth.application.convert.AuthPermissionDTOConverter;
import com.tencent.auth.application.convert.AuthRolePermissionDTOConverter;
import com.tencent.auth.application.dto.AuthPermissionDTO;
import com.tencent.auth.application.dto.AuthRolePermissionDTO;
import com.tencent.auth.common.entity.Result;
import com.tencent.auth.domain.entity.AuthPermissionBO;
import com.tencent.auth.domain.entity.AuthRolePermissionBO;
import com.tencent.auth.domain.service.AuthPermissionDomainService;
import com.tencent.auth.domain.service.AuthRolePermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限controller
 */
@RestController
@RequestMapping("/rolePermission/")
@Slf4j
public class RolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;
    /**
     * 新增角色权限关联关系
     */
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()),"权限关联不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色id不能为空");

             AuthRolePermissionBO rolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBO(authRolePermissionDTO);

            if (log.isInfoEnabled()) {
                log.info("Converted authRolePermissionBO: {}", JSON.toJSONString(rolePermissionBO));
            }
            return Result.ok(authRolePermissionDomainService.add(rolePermissionBO));

        } catch (Exception e) {
            log.error("RolePermissionController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增角色权限失败");
        }
    }



}
