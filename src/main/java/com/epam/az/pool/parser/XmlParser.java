package com.epam.az.pool.parser;

public interface XmlParser {
    Object parseXml(Class rootClass, String rootList, String path);

}
