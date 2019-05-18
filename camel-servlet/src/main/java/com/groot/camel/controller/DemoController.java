package com.groot.camel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/demo")
@RestController
public class DemoController {


    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "hello camel servlet";
    }
}
