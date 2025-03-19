package com.tencent.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.tencent.oss.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
          objectName = objectName+"/"+uploadFile.getName();
        return fileService.uploadFile(uploadFile, bucket, objectName);
    }


}
