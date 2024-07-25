package com.java.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.blog.config.Test;

@RestController
public class TestController {

    @Autowired
    private Test test;

    @GetMapping("/hello")
    public String hello() {
        return "Welcome";
    }
    @GetMapping("/greet")
    public String greet() {
        return test.greet();
    }
}
