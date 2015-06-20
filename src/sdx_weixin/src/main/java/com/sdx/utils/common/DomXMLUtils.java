
package com.sdx.utils.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomXMLUtils
{
    private static TransformerFactory tf = TransformerFactory.newInstance();

    private static DocumentBuilder builder;

    private static TransformerFactory tffactory;
    static
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            builder = factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        // 格式化工厂对象
        tffactory = TransformerFactory.newInstance();
    }

    public static String domToString(Document doc) throws TransformerException
    {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        return writer.toString();
    }

    public static Document parseDom(InputStream is) throws SAXException, IOException
    {
        return builder.parse(is);
    }

    public static Document newDocument()
    {
        return builder.newDocument();
    }

    public static void domToOutputStream(Document doc, OutputStream os) throws UnsupportedEncodingException, TransformerException
    {
        Transformer transformer = tffactory.newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(os, "utf-8")));
    }

    public static String getContentByName(Document doc, String name)
    {
        NodeList list = doc.getElementsByTagName(name);
        if (list != null && list.getLength() > 0)
            return list.item(0).getTextContent();
        return "";
    }
}
