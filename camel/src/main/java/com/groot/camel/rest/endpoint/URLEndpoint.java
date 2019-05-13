package com.groot.camel.rest.endpoint;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

public class URLEndpoint extends DefaultEndpoint {


    public Producer createProducer() throws Exception {
        return null;
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return null;
    }

    public boolean isSingleton() {
        return false;
    }
}
