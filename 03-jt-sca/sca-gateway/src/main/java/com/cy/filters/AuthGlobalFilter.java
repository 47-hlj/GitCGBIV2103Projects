package com.cy.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 47HLJ
 * @date 2021/7/5 16:38
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 通过filter方法处理客户端的请求
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求对象
        ServerHttpRequest request=exchange.getRequest();
        //获取请求参数
        MultiValueMap<String,String> queryParams =request.getQueryParams();
        String tokenValue=queryParams.getFirst("tokenKey");
        System.out.println("tokenValue="+tokenValue);
        //检查请求参数中是否有token、token的值是否为admin
        if(tokenValue==null||!"admin".equals(tokenValue)){
            ServerHttpResponse response=exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //假如已认证则执行下一步操作
        System.out.println("==AuthGlobalFilter,filter==");
        //执行过滤链
        return chain.filter(exchange);
    }

    /**
     * 定义过滤器优先级
     * @return
     */
    @Override
    public int getOrder() {
        //数字越小优先级越高
        return -100;
    }
}
