package com.groot.knowledge.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WsClient {

    public static void main(String[] args) throws IOException {
        //服务的地址
        URL wsUrl = new URL("http://127.0.0.1:8888/hello/sayHello");
        HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        OutputStream os = conn.getOutputStream();
        //请求体
        String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:q0=\"http://127.0.0.1:8888/hello/\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
                "<soapenv:Body> " +
                "<q0:sayHello>" +
                "<arg0>aaa</arg0>" +
                "</q0:sayHello> " +
                "</soapenv:Body> " +
                "</soapenv:Envelope>";
        os.write(soap.getBytes());
        if(conn.getResponseCode() == 200) {
            InputStream is = conn.getInputStream();

            byte[] b = new byte[1024];
            int len = 0;
            String s = "";
            while((len = is.read(b)) != -1){
                String ss = new String(b,0,len,"UTF-8");
                s += ss;
            }
            System.out.println(s);
            is.close();
        }else {
            System.out.println("服务链接失败： "+conn.getResponseMessage());
        }
        os.close();
        conn.disconnect();
    }
}
