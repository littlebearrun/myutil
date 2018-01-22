package com.likang.myutil.util.xml;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.collections.SequencedHashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * repeatable & keypath : 'tagname' : { 'key' : {...}, 'key' : {...}, ...}
 * repeatable : 'tagname' : [ {...}, {...}, ... ]
 * keypath : 'key  : { ... }
 * arrayPath : [[{...}], [{...}]]
 *
 * @author a-kimura
 *
 */
public class Xml2JsonUtil {

    public List keyPaths = new ArrayList();

    public Map pathMaps = new HashMap();

    public List repeatables = new ArrayList();

    public List singles = new ArrayList();

    public List skips = new ArrayList();

    public List arrays = new ArrayList();

    public Map namespaceResolvers = new HashMap();

    public String basePath;



    public JSONObject xml2jsonObj(NodeList nodes) throws Exception {
        this.basePath = null;
        if (nodes == null || nodes.getLength() == 0)
            return null;
        Node baseNode = nodes.item(0).getParentNode();
        if (baseNode == null)
            return null;
        this.basePath = getXPath((Element) baseNode);
        Map map = new SequencedHashMap();
        nodelist2json(map, nodes);
        return new JSONObject(map);
    }

    public JSONObject xml2jsonObj(Element element) throws Exception {
        this.basePath = null;
        Node baseNode = element.getParentNode();
        if (baseNode != null && baseNode.getNodeType() == Node.ELEMENT_NODE){
            this.basePath = getXPath((Element) baseNode);
        }

        JSONObject obj = (JSONObject) node2json(element);
        return obj;
    }

    public String xml2json(NodeList nodes) throws Exception {
        JSONObject obj = xml2jsonObj(nodes);
        if (obj == null)
            return "";
        return obj.toString();
    }

    public String xml2json(Element element) throws Exception {
        JSONObject obj = xml2jsonObj(element);
        return obj.toString();
    }

    public String xml2json(String xml) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));
        Element root = doc.getDocumentElement();
        return xml2json(root);
    }

    public Object node2json(Element element) throws Exception {
        Map map = new SequencedHashMap();
        String xpath = getXPath(element);
        if (singles.contains(xpath)) {
            if (element.getFirstChild() != null)
                return element.getFirstChild().getNodeValue();
            else
                return "";
        }
        NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node attr = attrs.item(i);
            String name = attr.getNodeName();
            String value = attr.getNodeValue();
            map.put(name, value);
        }
        NodeList childs = element.getChildNodes();
        nodelist2json(map, childs);
        return new JSONObject(map);
    }

    public void nodelist2json(Map map, NodeList nodes) throws Exception {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case Node.TEXT_NODE:
                case Node.CDATA_SECTION_NODE:
                    String text = node.getNodeValue().trim();
                    if (text.length() > 0)
                        map.put("content", node.getNodeValue());
                    break;
                case Node.ELEMENT_NODE:
                    Element childElm = (Element) node;
                    String childXPath = getXPath(childElm);
                    if (skips.contains(childXPath)) {
                        nodelist2json(map, childElm.getChildNodes());
                    } else {
                        String childName = childElm.getNodeName();
                        boolean isRepeatable = repeatables.contains(childXPath);
                        boolean hasKey = keyPaths.contains(childXPath);
                        if (isRepeatable && hasKey) {
                            JSONObject obj = (JSONObject) map.get(childName);
                            if (obj == null) {
                                obj = new JSONObject();
                                map.put(childName, obj);
                            }
                            String attrName = (String) pathMaps.get(childXPath);
                            String attrValue = childElm.getAttribute(attrName);
                            obj.put(attrValue, node2json(childElm));
                        } else if (isRepeatable && !hasKey) {
                            JSONArray obj = (JSONArray) map.get(childName);
                            System.out.println(obj);
                            if (obj == null) {
                                obj = new JSONArray();
                                map.put(childName, obj);
                            }
                            obj.add(node2json(childElm));
                        } else if (hasKey) {
                            String attrName = (String) pathMaps.get(childXPath);
                            String attrValue = childElm.getAttribute(attrName);
                            map.put(attrValue, node2json(childElm));
                        } else {
                            if(map.containsKey(childName)){
                                List alist = null ;
                                try {
                                    alist = (List)map.get(childName);
                                    alist.add(node2json(childElm));
                                }catch (Exception e){
                                    alist = new ArrayList() ;
                                    alist.add(map.get(childName)) ;
                                }
                                map.put(childName,alist);
                            }else{
                                map.put(childName, node2json(childElm));
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public String getXPath(Element element) {
        if (element == null)
            return null;
        StringBuffer xpath = new StringBuffer();
        xpath.append("/");
        String uri = element.getNamespaceURI();
        String prefix = (String) namespaceResolvers.get(uri);
        if (prefix != null)
            xpath.append(prefix).append(":");
        xpath.append(getTagName(element));
        Element parent = element;
        try {
            while (true) {
                parent = (Element) parent.getParentNode();
                if (parent == null)
                    break;
                xpath.insert(0, getTagName(parent));
                uri = parent.getNamespaceURI();
                prefix = (String) namespaceResolvers.get(uri);
                if (prefix != null)
                    xpath.insert(0, prefix + ":");
                xpath.insert(0, "/");
            }
        } catch (ClassCastException e) {

        }
        String xpathStr = xpath.toString();
        if (this.basePath != null)
            xpathStr = xpathStr.replaceFirst("^" + this.basePath, "");
        return xpathStr;
    }

    public String getTagName(Element elem) {
        String name = elem.getLocalName();
        if (name == null)
            name = elem.getNodeName();
        return name;
    }

    public static void main(String a[]) throws Exception {
        Xml2JsonUtil x2j = new Xml2JsonUtil();
        String json = x2j.xml2json("<?xml version='1.0' encoding='utf-8' ?><MyCardMemberServiceAuthResult xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns='http://tempuri.org/'><ReturnMsgNo><a>111</a><b>222</b></ReturnMsgNo> <ReturnTradeSeq>MMS0705080000000167</ReturnTradeSeq> <ReturnAuthCode>500AF1677D034D138A6D3AF78FF4B19A</ReturnAuthCode></MyCardMemberServiceAuthResult>");
        System.out.println(json);
    }
}
