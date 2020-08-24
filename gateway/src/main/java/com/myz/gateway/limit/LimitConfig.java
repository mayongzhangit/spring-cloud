package com.myz.gateway.limit;

import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/18 17:50
 * @email 2641007740@qq.com
 */
@Configuration
public class LimitConfig {

    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }


    /**
     * @see {@link RequestRateLimiterGatewayFilterFactory}
     */
    @Bean
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

//    /**
//     * @see {@link RequestRateLimiterGatewayFilterFactory}
//     */
//    @Bean
//    @Primary
//    public RateLimiter guavaRateLimit(){
//
//        return new RateLimiter() {
//            @Override
//            public Mono<Response> isAllowed(String routeId, String id) {
//                return null;
//            }
//
//            @Override
//            public Map getConfig() {
//                return null;
//            }
//
//            @Override
//            public Class getConfigClass() {
//                return null;
//            }
//
//            @Override
//            public Object newConfig() {
//                return null;
//            }
//        };
//    }
}