package com.groot.camel.controller;

import com.groot.camel.common.Response;
import com.groot.camel.router.ServletRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/demo")
@RestController
public class DemoController {

    @Autowired
    private ServletRouter servletRouter;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "hello camel servlet";
    }


    /**
     * 不起作用
     * @param request
     * @return
     */
    @GetMapping("/route")
    public Response route(HttpServletRequest request) {
        String path = request.getParameter("path");
        servletRouter.dynamic(path);
        return new Response<String>(200,"","add dynamic route success");
    }
}
