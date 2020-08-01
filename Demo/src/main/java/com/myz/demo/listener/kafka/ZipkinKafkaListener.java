package com.myz.demo.listener.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/1 15:41
 * @email 2641007740@qq.com
 */
@Slf4j
@Component
public class ZipkinKafkaListener {

    @KafkaListener(id = "myz-zipkin-kafka",groupId = "game_sc-demo_zipkinConsumeGroup",topics = {"game_sc-demo_zipkin"})
    public void receive(String message){
        log.info("Receive Message:{}", message);
    }

}
