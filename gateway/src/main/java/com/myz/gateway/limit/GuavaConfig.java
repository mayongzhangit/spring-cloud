package com.myz.gateway.limit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/18 18:49
 * @email 2641007740@qq.com
 */
public class GuavaConfig {

    private Cache<String,Integer> cache = CacheBuilder.newBuilder()
                                            .expireAfterWrite(1, TimeUnit.SECONDS)
                                            .build();
}
