package com.example.prometheus.study.controller;

import com.example.prometheus.study.metrics.TestMetrics;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HaiLang
 * @date 2022/2/17 15:51
 */
@RestController
public class TestController implements InitializingBean {

    private final AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private TestMetrics testMetrics;

    @Override
    public void afterPropertiesSet() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(this::hello, 0, 5, TimeUnit.SECONDS);
    }

    @GetMapping("/hello")
    public String hello() {
        testMetrics.getCounter().increment();
        testMetrics.getMap().put("x", (double) count.incrementAndGet());
        return "hello";
    }

}
