package com.groot.camel.controller;

import com.groot.camel.common.Response;
import com.groot.camel.router.ServletRouter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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



    @GetMapping("/route")
    public Response route(HttpServletRequest request) {
        String path = request.getParameter("path");
//        servletRouter.from("servlet:"+path)
//                .process(new Processor() {
//                    public void process(Exchange exchange) throws Exception {
//                        System.out.println("add dynamic route");
//                    }
//                }).transform().simple("hello "+path);
//        servletRouter.getRouteCollection().getRoutes().add()
        return new Response<String>(200,"","add dynamic route success");
    }
}
