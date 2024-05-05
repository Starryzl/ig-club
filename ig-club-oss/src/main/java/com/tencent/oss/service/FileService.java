package com.tencent.oss.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final StorageService storageService;

    public FileService(StorageService storageService){
        this.storageService = storageService;
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket(){
        return storageService.getAllBucket();
    }



}
