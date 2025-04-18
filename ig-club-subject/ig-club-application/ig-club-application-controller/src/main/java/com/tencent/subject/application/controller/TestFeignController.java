package com.tencent.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.tencent.subject.common.entity.PageResult;
import com.tencent.subject.infra.basic.entity.SubjectInfoEs;
import com.tencent.subject.infra.basic.service.SubjectEsService;
import com.tencent.subject.infra.entity.UserInfo;
import com.tencent.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


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
