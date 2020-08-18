package com.myz.gateway.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/18 18:55
 * @email 2641007740@qq.com
 */
public class GuavaRateLimitConfig {

    private static final RateLimiter limiter = RateLimiter.create(10);

    public void test (){
        boolean success = limiter.tryAcquire(10, TimeUnit.SECONDS);

    }

}
