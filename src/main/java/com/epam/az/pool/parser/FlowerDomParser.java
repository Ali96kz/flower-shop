package com.epam.az.pool.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class FlowerDomParser extends FlowersXmlParser {

    @Override
    public Object parseXml(Class rootClass, String rootList, String path) {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("flowers");

            extractFromNodeList(nList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getResult();
    }

    public void extractFromNodeList(NodeList nodeList) {
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node tempNode = nodeList.item(temp);

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
            }
        }
    }
}

