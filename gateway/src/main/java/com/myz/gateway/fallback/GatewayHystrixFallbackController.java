package com.myz.gateway.fallback;

import com.myz.common.util.ApiResult;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.cloud.gateway.support.ServiceUnavailableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/fallback")
    public ApiResult fallback(ServerWebExchange serverWebExchange){
        LinkedHashSet<URI> originalReqUrls = serverWebExchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        Boolean routed = serverWebExchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ALREADY_ROUTED_ATTR);
        URI firstOriginReqUrl = originalReqUrls.iterator().next();

        Throwable throwable = serverWebExchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);

        String code = ApiResult.ERROR_CODE;
        String msg = ApiResult.ERROR_MSG;

        if (throwable instanceof HystrixTimeoutException){ // 为什么没有找到服务会变成HystrixTimeoutException？
            code = "time-out";
            msg = "超时";
            log.error("time-out originalReqUrl0={},size={} routed?{}",firstOriginReqUrl.toString(),originalReqUrls.size(),routed);
        }else if (throwable instanceof ServiceUnavailableException){
            code = "service-unavailable";
            msg = "服务不可用";
            log.error("service-unavailable originalReqUrl0={},size={} routed?{}",firstOriginReqUrl.toString(),originalReqUrls.size(),routed);
        }else {
            log.error("unknown originalReqUrl0={},size={} routed?{} throwable",firstOriginReqUrl.toString(),originalReqUrls.size(),routed,throwable);
        }
        return ApiResult.build(code,msg,null);
    }
}

