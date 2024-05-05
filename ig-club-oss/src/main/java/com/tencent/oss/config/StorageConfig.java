package com.tencent.oss.config;

import com.tencent.oss.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class StorageConfig {

    @Value("${storage.service.type}")
    private String storageType;

    @Resource
    private StorageService aliStorageServiceImpl;
    @Resource
    private StorageService minioStorageServiceImpl;

    @Bean
    public StorageService storageService(){
        if("minio".equals(storageType)){
            return minioStorageServiceImpl;
        }else if("aliyun".equals(storageType)){
            return aliStorageServiceImpl;
        }else {
            throw new IllegalArgumentException("未找到对应文件存储处理器");
        }
    }
}
