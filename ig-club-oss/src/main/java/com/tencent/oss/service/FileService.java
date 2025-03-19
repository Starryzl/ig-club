package com.tencent.oss.service;

import com.tencent.oss.adapter.StorageAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private final StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter){
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket(){
        return storageAdapter.getAllBucket();
    }

    /**
     * 获取文件路径
     */

    public String getUrl(String bucketName, String objectName) {
        return storageAdapter.getUrl(bucketName,objectName);
    }

    /**
     * 上传文件
     */

    public String uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        storageAdapter.uploadFile(uploadFile,bucket,objectName);
        return storageAdapter.getUrl(bucket, objectName);
    }
}
