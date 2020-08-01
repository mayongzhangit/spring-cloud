package com.myz.demo.controller;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/31 16:30
 * @email 2641007740@qq.com
 */
@Slf4j
@RestController
@RequestMapping("/zipkin")
public class ZipkinController {

    @Autowired
    private Sender sender;

    @GetMapping("/demo1")
    public String demo1() throws InterruptedException {
        // 构建一个Tracing
        Tracing tracing = Tracing.newBuilder()
                .spanReporter(AsyncReporter.create(sender))
                .localServiceName("sc-demo-1")
                .build();
        Tracer tracer = tracing.tracer();
//        tracer.nextSpan();//更加高级

        Span parentSpan = tracer.newTrace().name("parent-span").start();

        Span child1Span = tracer.newChild(parentSpan.context()).name("child1-span").start();
        Span child2Span = tracer.newChild(parentSpan.context()).name("child2-span").start();

        Thread.sleep(500);
        child1Span.finish();

        Thread.sleep(600);
        child2Span.finish();

        Thread.sleep(700);
        parentSpan.finish();
        return "done";
    }

    @GetMapping("/demo2")
    public String demo2() throws InterruptedException {
        // 构建一个Tracing
        Tracing tracing = Tracing.newBuilder()
                .spanReporter(AsyncReporter.create(sender))
                .localServiceName("sc-demo-2")
                .build();
        Tracer tracer = tracing.tracer();
//        tracer.nextSpan();//更加高级

        Span parentSpan = tracer.newTrace()
                .annotate("aaa")
                .name("parent-span")
                .start();

        Thread.sleep(500);
        parentSpan.finish();
        return "done";
    }
}
