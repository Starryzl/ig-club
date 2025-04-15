package com.tencent.subject.infra.basic.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class EsRestClient {
    public static Map<String, RestHighLevelClient> clientMap = new HashMap<>();

    @Resource
    private EsConfigProperties esConfigProperties;

    /**
     * 初始化方法，用于在对象创建后初始化Elasticsearch客户端。
     * 该方法从配置属性中获取Elasticsearch集群配置，并为每个集群初始化一个客户端。
     * 如果初始化成功，将客户端存储在clientMap中；如果失败，记录错误日志。
     */
    @PostConstruct
    public void initialize() {
        List<EsClusterConfig> esConfigs = esConfigProperties.getEsConfigs();
        for (EsClusterConfig esConfig : esConfigs) {
            log.info("initialize.config.name:{},node:{}", esConfig.getName(), esConfig.getNodes());
            RestHighLevelClient restHighLevelClient = initRestClient(esConfig);
            if (restHighLevelClient != null) {
                clientMap.put(esConfig.getName(), restHighLevelClient);
            } else {
                log.error("config.name:{},node:{}.initError", esConfig.getName(), esConfig.getNodes());
            }
        }
    }

    /**
     * 初始化Elasticsearch的RestHighLevelClient。
     * 该方法根据配置中的节点信息创建一个RestHighLevelClient实例。
     */
    private RestHighLevelClient initRestClient(EsClusterConfig esClusterConfig) {
        String[] ipPortArr = esClusterConfig.getNodes().split(",");
        List<HttpHost> httpHostList = new ArrayList<>(ipPortArr.length);
        for (String ipPort : ipPortArr) {
            String[] ipPortInfo = ipPort.split(":");
            if (ipPortInfo.length == 2) {
                HttpHost httpHost = new HttpHost(ipPortInfo[0], NumberUtils.toInt(ipPortInfo[1]));
                httpHostList.add(httpHost);
            }
        }
        HttpHost[] httpHosts = new HttpHost[httpHostList.size()];
        httpHostList.toArray(httpHosts);

        RestClientBuilder builder = RestClient.builder(httpHosts);
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        return restHighLevelClient;
    }


}
