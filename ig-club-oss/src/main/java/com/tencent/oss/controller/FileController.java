package com.tencent.oss.controller;

import com.tencent.oss.util.MinioUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FileController {

    @Resource
    private MinioUtil minioUtil;

    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBuckets() throws Exception {
        List<String> allBucket = minioUtil.getAllBucket();
        return allBucket.get(0);
    }
}
