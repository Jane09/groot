package com.groot.knowledge.ws;

import com.groot.knowledge.json.XML;
import org.xml.sax.InputSource;

import javax.xml.soap.*;
import javax.xml.transform.sax.SAXSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class WsParser {

    public static void main(String[] args) throws SOAPException, IOException {
        String sessionId = "12";
        String extime = "2019";
        String time = "2018";
        int numSend = 2;
        String send = "<soap:Envelope xmlns:mrns0=\"http://sdp.SOMETHING.com/mapping/TSO\" xmlns:sdp=\"http://sdp.SOMETHING.com.tr/mapping/generated\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">"
                + "<soap:Header>"
                + "<sdp:token>"
                + "<sdp:sessionId>" + sessionId + "</sdp:sessionId>"
                + "</sdp:token>"
                + "<sdp:transaction-list>"
                + "<sdp:transaction-id>" + 11 + "</sdp:transaction-id>"
                + "</sdp:transaction-list>"
                + "</soap:Header>"
                + "<soap:Body>"
                + "<sdp:SendSMSInput>"
                + "<sdp:EXPIRY_DATE>" + extime + "</sdp:EXPIRY_DATE>"
                + "<sdp:MESSAGE_CLASS>0</sdp:MESSAGE_CLASS>"
                + "<sdp:S_DATE>" + time + "</sdp:S_DATE>"
                + "<sdp:SHORT_NUMBER>1905</sdp:SHORT_NUMBER>"
                + "<sdp:SRC_MSISDN>" + numSend + "</sdp:SRC_MSISDN>"
                + "<sdp:TO_RECEIVERS>"
                + "<sdp:msisdn>" + numSend + "</sdp:msisdn>"
                + "</sdp:TO_RECEIVERS>"
                + "<sdp:MESSAGE_BODY>"
                + "<sdp:message>Message body here.</sdp:message>"
                + "</sdp:MESSAGE_BODY>"
                + "</sdp:SendSMSInput>"
                + "</soap:Body>"
                + "</soap:Envelope>";
//        InputStream in = new ByteArrayInputStream(send.getBytes());
//        SOAPMessage message = MessageFactory.newInstance().createMessage(new MimeHeaders(), in);
//        System.out.println(message.getSOAPBody());

        SOAPMessage message = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createMessage();
        message.getSOAPPart().setContent(new SAXSource(new InputSource(new StringReader(send))));
        System.out.println(message.getSOAPBody());

//        System.out.println(XML.toJSONObject(send));;
    }
}
