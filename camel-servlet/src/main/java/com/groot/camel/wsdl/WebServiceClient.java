package com.groot.camel.wsdl;

import com.alibaba.fastjson.JSON;
import com.groot.userservice.GetUserByIdRequest;
import com.groot.userservice.GetUserByIdResponse;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WebServiceClient<Req,Rsp> {

    private static final String NAMESPACE = "http://www.groot.com/UserService";

    public static void main(String[] args) throws Exception {
        //注意事项
        // - 请求参数的路径要一样
        // - 名称要一样
        String address = "http://localhost:9090/services/userservice.wsdl?wsdl";
        WebServiceClient<GetUserByIdRequest, GetUserByIdResponse> client = new WebServiceClient();
        GetUserByIdRequest request = new GetUserByIdRequest();
        request.setUserId("1223");
        GetUserByIdResponse response = client.invoke(address,"GetUserById",request,false);
        System.out.println(JSON.toJSONString(response));
        //解决的问题，根据wsdl生成实体类
        
    }

    /**
     *
     * @param address
     * @param serviceClass
     * @param method
     * @param request
     * @param <Req>
     * @param <Rsp>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    public <Req,Rsp> Rsp invoke(String address,Class<?> serviceClass,String method, Req request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        JaxWsProxyFactoryBean factoryBean =new JaxWsProxyFactoryBean();
        factoryBean.setAddress(address);
        factoryBean.getOutInterceptors().add(new AuthInterceptor("root","root"));
        factoryBean.setServiceClass(serviceClass);
        Object target = factoryBean.create();
        Method mt = target.getClass().getMethod(method,request.getClass());
        return (Rsp)mt.invoke(target,request);
    }


    /**
     *
     * @param address
     * @param method
     * @param request
     * @param <Req>
     * @param <Rsp>
     * @return
     * @throws Exception
     */
    public <Req,Rsp> Rsp invoke(String address, String method, Req request,boolean secure) throws Exception {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(address);
        if(secure) {
            client.getOutInterceptors().add(new AuthInterceptor("root","root"));
        }
        Object[] rsps = client.invoke(method,request);
        return (Rsp)rsps[0];
    }
}
