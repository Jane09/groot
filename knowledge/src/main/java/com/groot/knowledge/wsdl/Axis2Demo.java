package com.groot.knowledge.wsdl;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;
import javax.xml.transform.Result;

public class Axis2Demo {

    public static void main(String[] args) throws AxisFault {
//        ServiceClient client = new ServiceClient();
//        String url = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx";
//        EndpointReference targetEPR = new EndpointReference(url);
//        Options options = client.getOptions();
//        options.setTo(targetEPR);
//        options.setAction("http://WebXml.com.cn/getRegionProvince");
//        OMFactory factory = OMAbstractFactory.getOMFactory();
//        OMNamespace namespace = factory.createOMNamespace("http://WebXml.com.cn/","");
//        OMElement method = factory.createOMElement("getSupportCityDataset",namespace);
//        method.build();
//        OMElement result = client.sendReceive(method);
//        System.out.println(result);
//        test();

        rpc();

        rpc2();
    }

    public static void test(){
        try{
        ServiceClient serviceClient = new ServiceClient();
        //创建服务地址WebService的URL,注意不是WSDL的URL
        String url = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        EndpointReference targetEPR = new EndpointReference(url);
        Options options = serviceClient.getOptions();
        options.setTo(targetEPR);
        //确定调用方法（wsdl 命名空间地址 (wsdl文档中的targetNamespace) 和 方法名称 的组合）
        options.setAction("http://WebXml.com.cn/getMobileCodeInfo");

        OMFactory fac = OMAbstractFactory.getOMFactory();
        /*
         * 指定命名空间，参数：
         * uri--即为wsdl文档的targetNamespace，命名空间
         * perfix--可不填
         */
        OMNamespace omNs = fac.createOMNamespace("http://WebXml.com.cn/", "");
        // 指定方法
        OMElement method = fac.createOMElement("getMobileCodeInfo", omNs);
        // 指定方法的参数
        OMElement mobileCode = fac.createOMElement("mobileCode", omNs);
        mobileCode.setText("15932582632");
        OMElement userID = fac.createOMElement("userID", omNs);
        userID.setText("");
        method.addChild(mobileCode);
        method.addChild(userID);
        method.build();

        //远程调用web服务
        OMElement result = serviceClient.sendReceive(method);
        System.out.println(result);

    } catch(AxisFault axisFault) {
        axisFault.printStackTrace();
    }
    }

    public static void rpc() throws AxisFault {
        RPCServiceClient serviceClient = new RPCServiceClient();
        // 创建WSDL的URL，注意不是服务地址
        String url = "http://127.0.0.1:8888/hello?wsdl";
        // 指定调用WebService的URL
        EndpointReference targetEPR = new EndpointReference(url);
        Options options = serviceClient.getOptions();
        // 确定目标服务地址
        options.setTo(targetEPR);
        // 确定调用方法（wsdl 命名空间地址 (wsdl文档中的targetNamespace) 和 方法名称 的组合）
        options.setAction("http://127.0.0.1:8888/hello/sayHello");
        // 指定方法的参数值
        Object[] parameters = new Object[] {"tom"};

        // 创建服务名称
        // 1.namespaceURI - 命名空间地址 (wsdl文档中的targetNamespace)
        // 2.localPart - 服务视图名 (wsdl文档中operation的方法名称，例如<wsdl:operation name="getMobileCodeInfo">)
        QName qname = new QName("http://127.0.0.1:8888/hello/", "sayHello");
        // 调用方法一 传递参数，调用服务，获取服务返回结果集
        OMElement element = serviceClient.invokeBlocking(qname, parameters);
        System.out.println(element);
        /*
         * 值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
         * 我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果
         */
        String result = element.getFirstElement().getText();
        System.out.println(result);

        // 调用方法二 getPrice方法并输出该方法的返回值
        // 指定方法返回值的数据类型的Class对象
        Class[] returnTypes = new Class[] {String.class};
        Object[] response = serviceClient.invokeBlocking(qname, parameters, returnTypes);
        String r = (String) response[0];
        System.out.println(r);
    }


    public static void rpc2() throws AxisFault {
//使用RPC方式调用WebService
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        //指定调用WebService的URL
        EndpointReference targetEPR =
                new EndpointReference("http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl");
        options.setTo(targetEPR);
        options.setAction("http://WebXml.com.cn/getRegionProvince");
        //指定方法的参数值
        Object[] opAddEntryArgs = new Object[] {};
        //指定要调用的方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://WebXml.com.cn/", "getRegionProvince");
        //调用法并输出该方法的返回值
        System.out.println(serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs));
    }
}
