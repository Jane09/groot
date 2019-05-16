package com.groot.camel.config;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.restlet.RestletConstants;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.restlet.data.Form;
import org.restlet.engine.adapter.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CamelConfig {

    @Autowired
    private CamelContext camelContext;

    @Bean("routeBuilder")
    public RouteBuilder newBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                restConfiguration()
                        .contextPath("/camel-rest")
                        .component("restlet")
                        .host("localhost")
                        .port(8089)
                        .bindingMode(RestBindingMode.json);
                onException(Exception.class).transform().simple("authfaild").stop();
                final RouteBuilder builder = this;
                rest("/say/hello")
                        .get()
                        .consumes("application/json")
                        .route()
                        .process(new Processor() {
                            //                    final AtomicBoolean retry = new AtomicBoolean(true);
                            public void process(Exchange exchange) throws Exception {//rest
                                System.out.println(exchange.getIn().getHeader(Exchange.HTTP_QUERY,String.class));
                                HttpRequest request = exchange.getIn().getHeader(RestletConstants.RESTLET_REQUEST,HttpRequest.class);
                                if(request != null){
                                    Map<String,String> headers = request.getHeaders().getValuesMap();
                                    System.out.println(headers);
                                    Form form = request.getResourceRef().getQueryAsForm();
                                    Map<String,String> pmap = form.getValuesMap();
                                    System.out.println(pmap.get("he"));
                                }
                            }
                        }).multicast() //并行的
                        .bean("bizService","m2")
                        .bean("bizService","m3")
                        .bean("bizService","m1")
                        .pipeline()//串行阻塞
                        .bean("bizService","m1")
                        .bean("bizService","m2")
                        .log(LoggingLevel.INFO,"invoke /say/hello")
                        .transform()
                        .simple("{\"hello\":\"world\"}");
                rest("/say/bye")
                        .get()
                        .consumes("application/json")
                        .route()
                        .transform().constant("Bye World")
                        .endRest();

                rest("/route/add")
                        .get()
                        .route().routeId("addRoute")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                HttpRequest request = exchange.getIn().getHeader(RestletConstants.RESTLET_REQUEST,HttpRequest.class);
                                if(request != null){
                                    Form form = request.getResourceRef().getQueryAsForm();
                                    Map<String,String> pmap = form.getValuesMap();
                                    String path = pmap.get("path");
                                    String value = pmap.get("out");
                                    builder.getRestCollection().rest(path).get().route()
                                            .to("stream:out")
                                            .log(value)
                                            .transform().simple(value);
                                }
                            }
                        });
            }
        };
    }
}
