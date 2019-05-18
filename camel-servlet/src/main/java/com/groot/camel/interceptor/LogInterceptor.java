package com.groot.camel.interceptor;

import lombok.extern.java.Log;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.processor.DelegateAsyncProcessor;
import org.apache.camel.spi.InterceptStrategy;
import org.springframework.stereotype.Component;

/**
 * 拦截所有router，输出日志
 */
@Component
@Log
public class LogInterceptor implements InterceptStrategy {


    public Processor wrapProcessorInInterceptors(CamelContext context, ProcessorDefinition<?> definition,
                                                 final Processor target, Processor nextTarget) throws Exception {
        return new DelegateAsyncProcessor(new Processor() {
            public void process(Exchange exchange) throws Exception {
                log.info("Before the processor");
                target.process(exchange);
                log.info("After the processor");
            }
        });
    }
}
