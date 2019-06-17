package com.groot.knowledge.wsdl;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import javax.xml.transform.Result;

public class Axis2Demo {

    public static void main(String[] args) throws AxisFault {
        ServiceClient client = new ServiceClient();
        String url = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx";
        EndpointReference targetEPR = new EndpointReference(url);
        Options options = client.getOptions();
        options.setTo(targetEPR);
        options.setAction("http://WebXml.com.cn/getRegionProvince");
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace namespace = factory.createOMNamespace("http://WebXml.com.cn/","");
        OMElement method = factory.createOMElement("getSupportCityDataset",namespace);
        method.build();
        OMElement result = client.sendReceive(method);
        System.out.println(result);
        test();
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
}
