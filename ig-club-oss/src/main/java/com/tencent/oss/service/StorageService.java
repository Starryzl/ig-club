package com.tencent.oss.service;

import com.tencent.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface StorageService {
    /**
     * 创建bucket桶
     */
    void createBucket(String bucket);

    /**
     * 上传文件
     */
    void uploadFile(MultipartFile uploadFile, String bucket, String objectName);

    /**
     * 列出所有桶
     */
    List<String> getAllBucket();

    /**
     * 列出当前桶及文件
     */
    List<FileInfo> getAllFile(String bucket);

    /**
     * 下载文件
     *
     * @param bucket
     * @param objectName
     * @return
     */
    InputStream downLoad(String bucket, String objectName);

    /**
     * 删除桶
     *
     * @param bucket
     * @throws Exception
     */
    void deleteBucket(String bucket);

    /**
     * 删除文件
     *
     * @param bucket
     * @param objectName
     * @throws Exception
     */
    void deleteObjectName(String bucket, String objectName);
}
