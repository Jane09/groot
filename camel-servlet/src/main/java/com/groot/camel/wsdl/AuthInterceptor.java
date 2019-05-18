package com.groot.camel.wsdl;


import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private String username;
    private String password;

    public AuthInterceptor(String username,String password) {
        super(Phase.PREPARE_SEND);
        this.username = username;
        this.password = password;
    }

    public void handleMessage(SoapMessage soapMessage) throws Fault {
        List<Header> headers = soapMessage.getHeaders();
        Document doc = DOMUtils.createDocument();
        Element auth = doc.createElementNS("http://cxf.wolfcode.cn/","SecurityHeader");
        Element UserName = doc.createElement("username");
        Element UserPass = doc.createElement("password");
        UserName.setTextContent(username);
        UserPass.setTextContent(password);
        auth.appendChild(UserName);
        auth.appendChild(UserPass);
        headers.add(0, new Header(new QName("SecurityHeader"),auth));
    }
}
