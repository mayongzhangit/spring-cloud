package com.myz.gateway.fallback;

import com.myz.common.util.ApiResult;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.cloud.gateway.support.ServiceUnavailableException;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.util.LinkedHashSet;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/14 16:55
 * @email 2641007740@qq.com
 */
@Slf4j
@RestController
@RequestMapping("/gateway-hystrix")
public class GatewayHystrixFallbackController {

    @Autowired
    private Environment environment;

    @GetMapping("/fallback")
    public ApiResult fallback(ServerWebExchange serverWebExchange){
        LinkedHashSet<URI> originalReqUrls = serverWebExchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        Route route = serverWebExchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        Boolean routed = serverWebExchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ALREADY_ROUTED_ATTR);
        Throwable throwable = serverWebExchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
        URI firstOriginReqUrl = originalReqUrls.iterator().next();

        String code = ApiResult.ERROR_CODE;
        String msg = ApiResult.ERROR_MSG;

        String errorDetail = String.format("originalReqUrl0=%s ,size=%d,routed?%b,targetRoute=%s",firstOriginReqUrl.toString(),originalReqUrls.size(),routed,route);
        if (throwable instanceof HystrixTimeoutException){ // 为什么没有找到服务会变成HystrixTimeoutException？
            code = "hystrix-timeout";
            msg = "hystrix超时 当前hystrix超时时间";
            log.error("hystrix-timeout {}",errorDetail);
        }else if (throwable instanceof ServiceUnavailableException){
            code = "service-unavailable";
            msg = "服务不可用";
            log.error("service-unavailable {}",errorDetail);
        }else if (throwable instanceof ResponseStatusException || throwable instanceof TimeoutException){ // NettyRoutingFilter
            code = "netty-timeout";
            msg = "netty超时 当前responseTime设置值="+environment.getProperty("spring.cloud.gateway.httpclient.responseTimeout");
            log.error("netty-timeout {}",errorDetail);
        }else if (throwable instanceof java.util.concurrent.TimeoutException){
            java.util.concurrent.TimeoutException timeoutException = (java.util.concurrent.TimeoutException)throwable;
            String message = timeoutException.getMessage();
            if (StringUtils.equals("Acquire operation took longer then configured maximum time",message)){ // FixedChannelPool
                log.error("netty-pool-acquire-timeout {}",errorDetail);
            }
        }else {
            log.error("unknown {} ,throwable",errorDetail,throwable);
        }
        return ApiResult.build(code,msg,null);
    }
}

