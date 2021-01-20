package com.myz.consumer.ribbon;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yzMa
 * @desc
 * @date 2021/1/20 17:04
 * @email 2641007740@qq.com
 */
@Slf4j
public class MyPing implements IPing {

    @Override
    public boolean isAlive(Server server) {
        log.info("my ping !!!");
        return true;
    }
}
