package com.interaction.utils;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DoXmlWithDOM{
	public static void main(String[] args) {
		File file = new File("F:demo.xml");
		(new DoXmlWithDOM()).readXML(file);
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
