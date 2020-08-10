package com.myz.demo.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/1 15:55
 * @email 2641007740@qq.com
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private Random random = new Random();

    @GetMapping("/send")
    public void send(String message){

        kafkaTemplate.send("game_zipkin_collector","message"+random.nextInt());
    }
}

