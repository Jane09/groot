package com.groot.knowledge.ws;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(serviceName="MyService",targetNamespace="http://127.0.0.1:8888/hello/")
public class HelloService {

    @WebMethod(operationName="sayHello",action = "http://127.0.0.1:8888/hello/sayHello")
    @WebResult(name="myReturn")
    public String sayHello(@WebParam(name="name") String name){
        return  "hello: " + name;
    }

    @WebMethod(exclude=true)//当前方法不被发布出去
    public String sayHello2(String name){
        return "hello " + name;
    }

    public static void main(String[] args) {
        /**
         * 参数1：服务的发布地址
         * 参数2：服务的实现者
         *  Endpoint  会重新启动一个线程
         */
        Endpoint.publish("http://127.0.0.1:8888/hello", new HelloService());
        System.out.println("Server ready...");
    }
}
