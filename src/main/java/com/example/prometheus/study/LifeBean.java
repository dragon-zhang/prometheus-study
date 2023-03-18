package com.example.prometheus.study;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author HaiLang
 * @date 2023/3/18 10:35
 */
@Component
public class LifeBean {
    
    @EventListener
    public void listenApplicationStartingEvent(ApplicationStartingEvent event) {
        System.out.println("ApplicationStartingEvent");
    }
    
    @EventListener
    public void listenApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        System.out.println("ApplicationEnvironmentPreparedEvent");
    }
    
    @EventListener
    public void listenApplicationContextInitializedEvent(ApplicationContextInitializedEvent event) {
        System.out.println("ApplicationContextInitializedEvent");
    }
    
    @EventListener
    public void listenApplicationPreparedEvent(ApplicationPreparedEvent event) {
        System.out.println("ApplicationPreparedEvent");
    }
    
    @EventListener
    public void listenContextStartedEvent(ContextStartedEvent event) {
        System.out.println("ContextStartedEvent");
    }
    
    @EventListener
    public void listenWebServerInitializedEvent(WebServerInitializedEvent event) {
        //有2个子类
        System.out.println("WebServerInitializedEvent");
    }
    
    @EventListener
    public void listenContextRefreshedEvent(ContextRefreshedEvent event) {
        System.out.println("ContextRefreshedEvent");
    }
    
    @EventListener
    public void listenApplicationStartedEvent(ApplicationStartedEvent event) {
        System.out.println("ApplicationStartedEvent");
    }
    
    @EventListener
    public void listenApplicationReadyEvent(ApplicationReadyEvent event) {
        System.out.println("ApplicationReadyEvent");
    }
    
    @EventListener
    public void listenContextClosedEvent(ContextClosedEvent event) {
        System.out.println("ContextClosedEvent");
    }
    
    @EventListener
    public void listenContextStoppedEvent(ContextStoppedEvent event) {
        System.out.println("ContextStoppedEvent");
    }
}
