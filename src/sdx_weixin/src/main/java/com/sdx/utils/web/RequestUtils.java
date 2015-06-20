
package com.sdx.utils.web;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;

public class RequestUtils extends ServletRequestUtils
{

    public static BigDecimal getBigDecimalParameter(ServletRequest request, String name, int defaultVal)
    {

        int i = getIntParameter(request, name, defaultVal);

        return new BigDecimal(i);
    }

    public static void bridgeRequestAttr(ServletRequest request, String... attrNames)
    {
        String value = null;
        if (null == attrNames)
        {
            return;
        }
        for (String attrName : attrNames)
        {
            value = request.getParameter(attrName);
            if (null != value)
            {
                request.setAttribute(attrName, value);
            }
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public static void bridgeRequestAll(ServletRequest request)
    {
        Map pMap = request.getParameterMap();
        if (null == pMap)
        {
            return;
        }
        for (Iterator iter = pMap.entrySet().iterator(); iter.hasNext();)
        {
            Map.Entry e = (Entry) iter.next();
            String key = String.valueOf(e.getKey());
            String value = getStringParameter(request, key, null);
            if (StringUtils.isNotBlank(value))
            {
                request.setAttribute(key, value);
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void bridgeRequestAttr(ServletRequest request, Map<String, String> attrs)
    {
        if (null == attrs)
        {
            return;
        }
        String value = null;
        for (Iterator iter = attrs.entrySet().iterator(); iter.hasNext();)
        {
            Map.Entry<String, String> e = (Entry<String, String>) iter.next();
            if (StringUtils.isNotBlank(e.getKey()) && StringUtils.isNotBlank(e.getValue()))
            {
                value = getStringParameter(request, e.getKey(), null);
                if (StringUtils.isNotBlank(value))
                {
                    request.setAttribute(e.getValue(), value);
                }
            }
        }
    }

    public static String getNodeText(String xml, String nodeName)
    {
        String startTag = "<" + nodeName + ">";
        String endTag = "</" + nodeName + ">";
        int start = xml.indexOf(startTag);
        int end = xml.indexOf(endTag);
        if (start > 0 && end > 0)
        {
            return xml.substring(start + startTag.length(), end);
        }
        return null;
    }
}