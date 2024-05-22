package com.tencent.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.tencent.auth.application.convert.AuthRoleDTOConverter;
import com.tencent.auth.application.convert.AuthUserDTOConverter;
import com.tencent.auth.application.dto.AuthRoleDTO;
import com.tencent.auth.application.dto.AuthUserDTO;
import com.tencent.auth.common.entity.Result;
import com.tencent.auth.domain.entity.AuthRoleBO;
import com.tencent.auth.domain.entity.AuthUserBO;
import com.tencent.auth.domain.service.AuthRoleDomainService;
import com.tencent.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色controller
 */
@RestController
@RequestMapping("/role/")
@Slf4j
public class RoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;
    /**
     * 新增角色
     */
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()), "角色key不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()), "角色名称不能为空");


            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);

            if (log.isInfoEnabled()) {
                log.info("Converted AuthUserBO: {}", JSON.toJSONString(authRoleBO));
            }
            return Result.ok(authRoleDomainService.add(authRoleBO));

        } catch (Exception e) {
            log.error("RoleController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增角色失败");
        }
    }

    /**
     * 修改 角色
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(), "角色id不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.update(authRoleBO));

        } catch (Exception e) {
            log.error("RoleController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新角色信息失败");
        }
    }

    /**
     * 删除角色
     \
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);

            return Result.ok(authRoleDomainService.delete(authRoleBO));

        } catch (Exception e) {
            log.error("RoleController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除角色信息失败");
        }

    }


}
