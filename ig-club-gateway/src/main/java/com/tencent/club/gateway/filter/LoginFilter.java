package com.tencent.club.gateway.filter;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 登陆拦截器
 */
@Component
@Slf4j
public class LoginFilter implements GlobalFilter {
    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String url = request.getURI().getPath();
        log.info("LoginFilter.filter.url:{}",url);
        if(url.equals("/user/doLogin")){
            return chain.filter(exchange);
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        log.info("LoginFilter.filter.url:{}", new Gson().toJson(tokenInfo));
        String loginId = (String) tokenInfo.getLoginId();
        if (StringUtils.isEmpty(loginId)) {
            throw new Exception("未获取到用户信息");
        }
        mutate.header("loginId",loginId);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
