package com.myz.gateway.limit;

import com.google.common.util.concurrent.RateLimiter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/18 18:55
 * @email 2641007740@qq.com
 */
@Slf4j
@Component
public class GuavaRateLimit extends AbstractRateLimiter<GuavaRateLimit.Config> {

    public GuavaRateLimit(){
        super(Config.class, "guava-rate-limiter", null);
    }

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        Config config = getConfig().get(routeId);
        Assert.notNull(config,"routeId="+routeId+"对应的配置不应该为null");

        RateLimiter rateLimiter = config.getRateLimiter();
        if (rateLimiter == null){
            if (config.getWarmupPeriodMs() <=0){
                rateLimiter = RateLimiter.create(config.getPermitsPerSecond());
            }else{
                rateLimiter = RateLimiter.create(config.getPermitsPerSecond(),config.getWarmupPeriodMs(),TimeUnit.MILLISECONDS);
            }
            config.setRateLimiter(rateLimiter);
            log.info("init config={}with rateLimiter={}",config,rateLimiter);
        }
        boolean allowed = config.getRateLimiter().tryAcquire();
        log.info("config={},routeId={},id={},allowed={}",config,routeId,id,allowed);

        return Mono.just(new Response(allowed,new HashMap<>()));
    }

    /**
     * @see {@link AbstractRateLimiter#onApplicationEvent(org.springframework.cloud.gateway.event.FilterArgsEvent)}
     */
    static class Config {
        private int permitsPerSecond;
        private int warmupPeriodMs;// 预热周期（毫秒）
        private RateLimiter rateLimiter;
        public Config(){}

        public int getPermitsPerSecond() {
            return permitsPerSecond;
        }

        public void setPermitsPerSecond(int permitsPerSecond) {
            this.permitsPerSecond = permitsPerSecond;
        }

        public int getWarmupPeriodMs() {
            return warmupPeriodMs;
        }

        public void setWarmupPeriodMs(int warmupPeriodMs) {
            this.warmupPeriodMs = warmupPeriodMs;
        }

        public RateLimiter getRateLimiter() {
            return rateLimiter;
        }

        public void setRateLimiter(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "permitsPerSecond=" + permitsPerSecond +
                    ", warmupPeriodMs=" + warmupPeriodMs +
                    ", rateLimiter=" + rateLimiter +
                    '}';
        }
    }

}
