package example.main.java.cn.utils;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @author zhaoyegui
 * @date 2019-09-16 11:19
 */
public class XMLUtil {
    /**
     *  将XML转换成Map
     * @param inputStream 请求体中的输入流
     * @return Map
     * */
    public static Map<String,String> getMap(InputStream inputStream){
        //返回的Map
        Map<String,String> map = new HashMap<>();
        //初始化解析器
        SAXReader reader = new SAXReader();

        //读取XML文档
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //获取XML文档的根节点
        Element element = document.getRootElement();

        //遍历所有节点，把键和值存入Map
        List<Element> elementList = element.elements();
        for (Element e : elementList) {
            map.put(e.getName(),e.getText());
        }

        return map;
    }


    public static String toXml(Map<String, String> params){
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        buf.append("<xml>");
        for(String key : keys){
            buf.append("<").append(key).append(">");
            buf.append("<![CDATA[").append(params.get(key)).append("]]>");
            buf.append("</").append(key).append(">\n");
        }
        buf.append("</xml>");
        return buf.toString();
    }


    public static String parseXML(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                sb.append("<" + k + ">" + parameters.get(k) + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }


    public static Map<String,String> getMap(byte[] xmlBytes,String charset){
        //返回的Map
        Map<String,String> map = new HashMap<>();
        //初始化解析器
        SAXReader reader = new SAXReader();

        //读取XML文档
        Document document = null;
        try {
            InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
            source.setEncoding(charset);
            document= reader.read(source);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获取XML文档的根节点
        Element element = document.getRootElement();

        //遍历所有节点，把键和值存入Map
        List<Element> elementList = element.elements();
        for (Element e : elementList) {
            map.put(e.getName(),e.getText());
        }

        return map;
    }


}
