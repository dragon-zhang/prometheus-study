package com.example.prometheus.study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HaiLang
 * @date 2022/2/17 15:51
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
