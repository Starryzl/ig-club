package com.tencent.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.tencent.oss.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @NacosValue(value = "${storage.service.type}", autoRefreshed=true)
    private String storageType;

    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBuckets() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("/testNacos")
    public String testNacos() throws Exception {
        return storageType;
    }
}
