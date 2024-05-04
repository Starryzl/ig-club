package com.tencent.oss.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.minio.MinioClient;

/**
 * minio配置管理
 */
@Configuration
public class MinioConfig {

    /**
     * miniUrl
     */
    @Value("${minio.url}")
    private String url;
    /**
     * minio账户
     */
    @Value("${minio.accessKey}")
    private String accessKey;
    /**
     * minio密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;


    /**
     * 构造minioClient
     * @return
     */
    @Bean
    public MinioClient getMinioClient(){
        return MinioClient.builder().endpoint(url).credentials(accessKey,secretKey).build();
    }
}
