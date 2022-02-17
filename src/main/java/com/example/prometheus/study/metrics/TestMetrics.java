package com.example.prometheus.study.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HaiLang
 * @date 2022/2/17 16:04
 */
@Getter
@Component
public class TestMetrics implements MeterBinder {

    private Counter counter;

    private Gauge gauge;

    /**
     * Map对象，用于对该组件进行扩展
     */
    private final Map<String, Double> map = new HashMap<>();

    public TestMetrics() {
        map.put("x", -1.0);
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        // 定义并注册一个名称为prometheus.demo.counter的计数器，标签是name:counter1
        this.counter = Counter.builder("prometheus.demo.counter")
                .tag("name", "counter1")
                .description("demo counter")
                .register(meterRegistry);
        // 从业务端传递的Map中取出与Key对应的值放入注册的Gauge仪表盘中，标签是name:gauge1
        this.gauge = Gauge.builder("prometheus.demo.gauge", map, x -> x.get("x"))
                .tag("name", "gauge1")
                .description("This is Gauge")
                .register(meterRegistry);
    }
}
