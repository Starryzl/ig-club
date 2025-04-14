package com.tencent.subject.domain.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tencent.subject.domain.entity.SubjectCategoryBO;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 缓存工具类
 */
@Component
public class CacheUtil<K,V> {

    private Cache<String,String> localCache =
            CacheBuilder.newBuilder()
                    .maximumSize(5000)
                    .expireAfterWrite(10, TimeUnit.SECONDS)
                    .build();

    public List<V> getResult(String cacheKey, Class<V> clazz,
                             Function<String,List<V>> function){
        List<V> resultList = new ArrayList<>();
        String content = localCache.getIfPresent(cacheKey);
        if(StringUtils.isNotBlank(content)) {
            resultList = JSON.parseArray(content, clazz);
        }else {
            resultList = function.apply(cacheKey);
            if(!CollectionUtils.isEmpty(resultList)){
                localCache.put(cacheKey,JSON.toJSONString(resultList));
            }
        }
        return resultList;
    }

    public <K, V> Map<K, V> getMapResult(String cacheKey, Class<V> clazz,
                                         Function<String, Map<K, V>> function) {
        Map<K, V> resultMap = new HashMap<>();
        String content = localCache.getIfPresent(cacheKey);
        if (StringUtils.isNotBlank(content)) {
            resultMap = JSON.parseObject(content, new TypeReference<Map<K, V>>() {});
        } else {
            resultMap = function.apply(cacheKey);
            if (!CollectionUtils.isEmpty(resultMap)) {
                localCache.put(cacheKey, JSON.toJSONString(resultMap));
            }
        }
        return resultMap;
    }



}
