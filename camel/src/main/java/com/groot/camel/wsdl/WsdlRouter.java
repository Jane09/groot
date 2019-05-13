package com.groot.camel.wsdl;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.stereotype.Component;


@Component
public class WsdlRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        CxfEndpoint cxf = new CxfEndpoint();
//        cxf.setWsdlURL();

//        from("direct:start");
    }
}
