package com.groot.webservice.config;

import com.groot.webservice.common.Constants;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WsConfig {

    @Bean
    @SuppressWarnings("unchecked")
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, Constants.WS_);
    }

    //http://localhost:9090/ws/userservice.wsdl?wsdl
    @Bean(name = "userservice")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(Constants.US_PORTTYPE);
        wsdl11Definition.setServiceName("UserService");
        wsdl11Definition.setLocationUri(Constants.WS);
        wsdl11Definition.setTargetNamespace(Constants.US_NAMESPACE);
        wsdl11Definition.setSchema(usersSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("wsdl/userservice.xsd"));
    }
}
