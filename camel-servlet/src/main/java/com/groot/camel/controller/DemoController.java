package com.groot.camel.controller;

import com.groot.camel.common.Response;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/demo")
@RestController
public class DemoController {

    @Autowired
    private CamelContext camelContext;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "hello camel servlet";
    }

    /**
     * 🆗
     * 了
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/route2")
    public Response route2(HttpServletRequest request) throws Exception {
        String path = request.getParameter("path");
        RouteDefinition rd = new RouteDefinition();
        rd.from("servlet:"+path).routeId(path).process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("add dynamic route");
            }
        }).transform().simple("hello "+path);
        camelContext.addRouteDefinition(rd);
        return new Response<String>(200,"","add dynamic route "+path+" success");
    }
}
