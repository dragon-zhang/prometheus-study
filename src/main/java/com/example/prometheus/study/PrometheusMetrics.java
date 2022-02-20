package com.example.prometheus.study;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/qq_34556414/article/details/122898647
 *
 * @author HaiLang
 * @date 2022/2/20 11:37
 */
public class PrometheusMetrics {
    /**
     * 注重显示当前的值，比如qpm、队列长度
     */
    private static final Gauge GAUGE = Gauge.build()
            .name("http_request_total_qpm")
            .labelNames("path")
            .help("http_request_total_qpm")
            .register();
    /**
     * 总的统计数，只增不减，可以用于统计一段时间内接口的总请求数
     */
    private static final Counter COUNTER = Counter.build()
            .name("http_request_total")
            .labelNames("path", "type")
            .help("soul http request type total count")
            .register();
    /**
     * Histogram则是统计具体有多少请求数落在桶里
     */
    private static final Histogram HISTOGRAM = Histogram.build()
            .name("requests_latency_histogram_millis")
            .help("Requests Latency Histogram Millis (ms)")
            //这里设置了4个桶，0.3s、1s、2s、3s
            .buckets(0.3, 1, 2, 3)
            .register();
    /**
     * Summary统计可得出百分比结论，比如80%的请求都在0.167s内响应
     * 存在性能问题，看取舍
     */
    private static final Summary SUMMARY = Summary.build()
            .name("requests_latency_summary_millis")
            .help("Requests Latency Summary Millis (ms)")
            .quantile(0.5, 0.05)
            .quantile(0.8, 0.025)
            .quantile(0.95, 0.01)
            .quantile(0.99, 0.001)
            .maxAgeSeconds(TimeUnit.MINUTES.toSeconds(5))
            .ageBuckets(5)
            .register();

    public static void main(String[] args) throws IOException {
        HTTPServer server = new HTTPServer(9190);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            GAUGE.labels("http://localhost:9195/http/test/payment").set(ThreadLocalRandom.current().nextInt(1000));
        }, 100, 200, TimeUnit.MILLISECONDS);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            COUNTER.labels("http://localhost:9195/http/test/payment", "POST").inc(1);
        }, 100, 200, TimeUnit.MILLISECONDS);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            final Summary.Timer timer = SUMMARY.startTimer();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer.observeDuration();
        }, 100, 200, TimeUnit.MILLISECONDS);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            final Histogram.Timer timer = HISTOGRAM.startTimer();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer.observeDuration();
        }, 100, 200, TimeUnit.MILLISECONDS);
        System.out.println("started");
    }
}
