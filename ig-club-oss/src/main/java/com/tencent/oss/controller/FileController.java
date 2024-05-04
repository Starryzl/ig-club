package com.tencent.oss.controller;

import com.tencent.oss.service.StorageService;
import com.tencent.oss.util.MinioUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FileController {

    @Resource
    private StorageService minioStorageServiceImpl;

    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBuckets() throws Exception {
        List<String> allBucket = minioStorageServiceImpl.getAllBucket();
        return allBucket.get(0);
    }
}
