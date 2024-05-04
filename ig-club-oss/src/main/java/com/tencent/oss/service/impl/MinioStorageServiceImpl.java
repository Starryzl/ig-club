package com.tencent.oss.service.impl;

import com.tencent.oss.entity.FileInfo;
import com.tencent.oss.service.StorageService;
import com.tencent.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@Service("minioStorageServiceImpl")
public class MinioStorageServiceImpl implements StorageService {

    @Resource
    private MinioUtil minioUtil;

    @Override
    @SneakyThrows
    public void createBucket(String bucket) {
        minioUtil.createBucket(bucket);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        minioUtil.createBucket(bucket);
        if (objectName!=null){
            minioUtil.uploadFile(uploadFile.getInputStream(),bucket,objectName+"/"+uploadFile.getName());
        }else {
            minioUtil.uploadFile(uploadFile.getInputStream(),bucket,uploadFile.getName());
        }
    }

    @Override
    @SneakyThrows
    public List<String> getAllBucket() {
        return minioUtil.getAllBucket();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllFile(String bucket) {
        return minioUtil.getAllFile(bucket);
    }

    @Override
    @SneakyThrows
    public InputStream downLoad(String bucket, String objectName) {
        return minioUtil.downLoad(bucket,objectName);
    }

    @Override
    @SneakyThrows
    public void deleteBucket(String bucket) {
        minioUtil.deleteBucket(bucket);
    }

    @Override
    @SneakyThrows
    public void deleteObjectName(String bucket, String objectName) {
    minioUtil.deleteObjectName(bucket,objectName);
    }
}
