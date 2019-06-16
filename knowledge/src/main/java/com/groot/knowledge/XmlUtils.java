package com.groot.knowledge;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class XmlUtils {

    public static void main(String[] args) {
        String xmlstr = jsonToXml("{\"class\": {\"student\": [{\"age\": \"18\",\"gender\": \"男\",\"name\": \"张三\",\"qk\": [{\"q1\": \"001\",\"q2\": \"002\",\"q3\": \"003\"},{\"q1\": \"001\",\"q2\": \"002\",\"q3\": \"003\"}]},{\"age\": \"17\",\"gender\": \"男\",\"name\": \"李四\",\"qk\": {\"q1\": \"005\",\"q2\": \"006\",\"q3\": \"007\"}},{\"age\": \"19\",\"gender\": \"女\",\"name\": \"王五\",\"qk\": {\"q1\": \"008\",\"q2\": \"009\",\"q3\": \"010\"}}]}}");
		System.out.println(xmlstr);
        System.out.println(xmlToJson(xmlstr));
    }

    public static String xmlToJson(String xmlStr) {
        SAXBuilder builder = new SAXBuilder();
        Map<String,Object> map = new HashMap<>();
        Document doc;
        try{
            doc = builder.build(new InputSource(new StringReader(xmlStr)));
            Element root =doc.getRootElement();
            List<Element> children = root.getChildren();
            Map<String,Object> rootMap = new HashMap<>();
            rootMap = xmlToMap(children,rootMap);
            map.put(root.getName(),rootMap);
            return JSON.toJSONString(map);
        }catch (Exception ex) {
            return "{}";
        }
    }

    public static String jsonToXml(String json) {
        StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        JSONObject obj = JSON.parseObject(json);
        jsonToXmlStr(obj,buffer);
        return buffer.toString();
    }

    private static void jsonToXmlStr(JSONObject jObj, StringBuilder buffer) {
        if(null != jObj) {
            jObj.forEach((key,value) -> {
                if(value instanceof JSONObject) {
                    buffer.append("<").append(key).append(">");
                    jsonToXmlStr((JSONObject)value,buffer);
                    buffer.append("</").append(key).append(">");
                }else if(value instanceof JSONArray) {
                    JSONArray obarr = (JSONArray)value;
                    obarr.forEach(obj -> {
                        buffer.append("<").append(key).append(">");
                        jsonToXmlStr((JSONObject)obj,buffer);
                        buffer.append("</").append(key).append(">");
                    });
                }else {
                    buffer.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
                }
            });
        }
    }

    private static Map<String,Object> xmlToMap(List<Element> els, Map<String,Object> map) {
        els.forEach(el -> {
            Map<String,Object> eMap = new HashMap<>();
            List<Element> children = el.getChildren();
            if(children != null && children.size() > 0) {
                eMap = xmlToMap(children,eMap);
                Object ob = map.get(el.getName());
                if(null != ob) {
                    List<Object> olist = new ArrayList<>();
                    if(ob instanceof Map) {
                        olist.add(ob);
                        olist.add(eMap);
                    }else if(ob instanceof List) {
                        olist = (List<Object>) ob;
                        olist.add(eMap);
                    }
                    map.put(el.getName(),olist);
                }else {
                    map.put(el.getName(),eMap);
                }
            }else {
                map.put(el.getName(),el.getValue());
            }
        });
        return map;
    }
}
