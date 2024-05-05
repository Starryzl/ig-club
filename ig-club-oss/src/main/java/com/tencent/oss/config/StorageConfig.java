package com.tencent.oss.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.tencent.oss.adapter.StorageAdapter;
import com.tencent.oss.adapter.AliStorageAdapter;
import com.tencent.oss.adapter.MinioStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @NacosValue(value = "${storage.service.type}", autoRefreshed=true)
    private String storageType;



    @Bean
    public StorageAdapter storageService(){
        if("minio".equals(storageType)){
            return new MinioStorageAdapter();
        }else if("aliyun".equals(storageType)){
            return new AliStorageAdapter();
        }else {
            throw new IllegalArgumentException("未找到对应文件存储处理器");
        }
    }
}
