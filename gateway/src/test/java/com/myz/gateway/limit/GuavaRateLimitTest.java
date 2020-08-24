package com.myz.gateway.limit;

import com.google.common.util.concurrent.RateLimiter;
import com.myz.gateway.GatewayStartApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/24 10:26
 * @email 2641007740@qq.com
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayStartApp.class)
public class GuavaRateLimitTest {

    private RateLimiter rateLimiter = RateLimiter.create(5); //RateLimiter.create(5,1, TimeUnit.SECONDS);

    private ExecutorService fixedPool = Executors.newFixedThreadPool(10);

    private volatile boolean runFlag = true;

    @Before
    public void before(){

    }

    @After
    public void after(){
        fixedPool.shutdown();
    }

    @Test
    public void test() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("set up thread started ");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runFlag = false;
                log.info("set up flag to end ");
            }
        }).start();

        Random random = new Random();

        while (runFlag){

            AtomicInteger count = new AtomicInteger(0);

            for (int i=0;i<1000;i++){
                fixedPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        boolean acquireSuccess = rateLimiter.tryAcquire(1);
                        if (acquireSuccess){
                            log.info("acquire success");
                        }else {
                            count.incrementAndGet();
                        }
                    }
                });
//                Thread.sleep(random.nextInt(500));
            }
            log.info("limited count={}",count.get());

            Thread.sleep(1000);
        }
        log.info("successfully ended");

    }
}
