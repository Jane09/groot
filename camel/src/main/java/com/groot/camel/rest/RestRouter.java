package com.groot.camel.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.DefaultErrorHandlerBuilder;
import org.apache.camel.builder.ErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.restlet.RestletConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.restlet.data.Form;
import org.restlet.engine.adapter.HttpRequest;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 */
@Getter
@AllArgsConstructor
@Component
public class RestRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .contextPath("/camel-rest")
                .component("restlet")
                .host("localhost")
                .port(8089)
                .bindingMode(RestBindingMode.json);

        onException(Exception.class).transform().simple("authfaild").stop();//输出异常信息
//        onException(Throwable.class).retryWhile(new Predicate() {
//            public boolean matches(Exchange exchange) {
//                final AtomicBoolean retry = (AtomicBoolean)exchange.getProperty("retry");
//                return !retry.compareAndSet(true,false);
//            }
//        }).transform().simple("authfaild").stop();


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
//                        exchange.setProperty("retry",retry);
//                        throw new Exception("auth failed");
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
    }
}
