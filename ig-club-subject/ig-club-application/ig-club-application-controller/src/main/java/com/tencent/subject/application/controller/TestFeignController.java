package com.tencent.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.tencent.subject.application.convert.SubjectCategoryDTOConverter;
import com.tencent.subject.application.convert.SubjectLabelDTOConverter;
import com.tencent.subject.application.dto.SubjectCategoryDTO;
import com.tencent.subject.application.dto.SubjectLabelDTO;
import com.tencent.subject.application.util.LoginUtil;
import com.tencent.subject.common.entity.Result;
import com.tencent.subject.domain.entity.SubjectCategoryBO;
import com.tencent.subject.domain.service.SubjectCategoryDomainService;
import com.tencent.subject.infra.entity.UserInfo;
import com.tencent.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 刷题分类Controller
 */

@RestController
@RequestMapping("/subject/category")
@Slf4j
public class TestFeignController {

    @Resource
    private UserRpc userRpc;

    @GetMapping("testFeign")
    public void testFeign(){
        UserInfo userInfo = userRpc.getUserInfo("ig");
        log.info("testFeign.userInfo:{}",userInfo);

    }
}
