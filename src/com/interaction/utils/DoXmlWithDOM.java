package com.interaction.utils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DoXmlWithDOM{
	public static void main(String[] args) throws Exception {
		//File file = new File("F:function.xml");
		//(new DoXmlWithDOM()).readXML(file);
		
		Map<String, Object> result = getXml("F:function.xml");
		System.out.println(result);
	}

	
	public static Map<String, Object> getXml(String fileName) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new File(fileName));     
		Element root = document.getDocumentElement(); //获得文档的根节点
		
		NodeList nodeList = root.getChildNodes();   // 获得文档根元素下一级子元素所有元素;
		for (int i = 0; i < nodeList.getLength(); i++) {
			 Node child = nodeList.item(i);
		     // 获得child的属性;
		     if (child.getNodeType() == Node.ELEMENT_NODE) {
		    	 result.put("type", child.getAttributes().getNamedItem("type").getNodeValue());
		    	 result.put("classNameUpCase", child.getAttributes().getNamedItem("classNameUpCase").getNodeValue());
		    	 result.put("classNameLowCase", child.getAttributes().getNamedItem("classNameLowCase").getNodeValue());
		    	 result.put("entityUrl", child.getAttributes().getNamedItem("entityUrl").getNodeValue());
		     }
		     
		     for (Node outNode = child.getFirstChild(); outNode != null; outNode = outNode.getNextSibling()) {
		    	if (outNode.getNodeName().equals("attrs")) {
		    		Map<String, Object> attrs = new HashMap<>();
			    	 for (Node inNode = outNode.getFirstChild(); inNode != null; inNode = inNode.getNextSibling()) {
			    		 if (inNode.getNodeType() == Node.ELEMENT_NODE) {
							attrs.put(inNode.getAttributes().getNamedItem("attrName").getNodeValue(),
									inNode.getAttributes().getNamedItem("desc").getNodeValue());
						}
				     }
			    	 if (attrs != null && attrs.size() != 0) {
						result.put("attrs",attrs);
					}
				}
		    	
		    	if (outNode.getNodeName().equals("paras")) {
		    		Map<String, Object> paras = new HashMap<>();
			    	 for (Node inNode = outNode.getFirstChild(); inNode != null; inNode = inNode.getNextSibling()) {
			    		 if (inNode.getNodeType() == Node.ELEMENT_NODE) {
			    			 paras.put(inNode.getAttributes().getNamedItem("paraName").getNodeValue(),
									inNode.getAttributes().getNamedItem("value").getNodeValue());
						}
				     }
			    	 if (paras != null && paras.size() != 0) {
						result.put("paras",paras);
					}
				}
		     }
		}
		
		return result;
	}
	
	
	
	private void readXML(File file) {

		int cout=1;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			NodeList nodes = doc.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				NodeList nodeList = node.getChildNodes();
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node node2 = nodeList.item(j);
					NodeList nodeList2 = node2.getChildNodes();
					
					for (int k = 0; k < nodeList2.getLength(); k++) {
						System.out.println(cout+"===>"+nodeList2.item(k).getNodeName()+":"+nodeList2.item(k).getTextContent());
					}
					cout++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	
}
