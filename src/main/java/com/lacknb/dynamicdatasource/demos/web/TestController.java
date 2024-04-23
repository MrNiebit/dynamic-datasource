package com.lacknb.dynamicdatasource.demos.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gitsilence
 * @date 2024-04-23
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello " + name;
    }

}
