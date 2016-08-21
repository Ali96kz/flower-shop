package com.epam.az.flower.shop.parser;

public interface XmlParser {
    Object parseXml(Class rootClass, String rootList, String path);

}
