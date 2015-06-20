
package com.sdx.utils.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

public abstract class StringUtil
{

    public static String ChangeSimplePoint(String input)
    {
        if (input == null)
        {
            return "";
        }
        return input.replace("\"", "\'");
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> changeRequestMap(HttpServletRequest request)
    {
        Map<String, String> returnMap = new HashMap<String, String>();
        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet())
        {
            String[] item = map.get(key);
            if (item.length > 1)
            {
                String values = item[0];
                for (int i = 1; i < item.length; i++)
                {
                    values = values + "," + item[i];
                }
                returnMap.put(key, values);
            }
            else
            {
                returnMap.put(key, item[0]);
            }
        }
        return returnMap;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String[]> changeParameterMap(HttpServletRequest request)
    {
        Map<String, String[]> returnMap = new HashMap<String, String[]>();
        returnMap.putAll(request.getParameterMap());
        return returnMap;
    }

    public static Map<String, String[]> changeParameterMap(Map<String, String[]> map)
    {
        Map<String, String[]> returnMap = new HashMap<String, String[]>();
        returnMap.putAll(map);
        return returnMap;
    }

    /**
     * 过滤html代码
     * @param inputString
     * @return
     */
    public static String filterHtml(String inputString)
    {

        if (inputString != null)
        {
            // inputString = inputString.replaceAll("\n", "")
            // .replaceAll("\r", "").replaceAll("\t", ""); // 含html标签的字符串

            if (inputString.indexOf(">") > -1)
            {
                inputString = inputString.replaceAll(">", "");
            }
            if (inputString.indexOf("<") > -1)
            {
                inputString = inputString.replaceAll("<", "");
            }
            /*
             * if(inputString.indexOf("&")>-1) {
             * inputString=inputString.replaceAll("&", ""); }
             */
        }
        return inputString;
    }

    static String reg = "(?:')|(?:--)|"
            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|1=1)\\b)";

    static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

    /**
     * 过滤SQL注入
     * @param sqlStr
     * @return
     */
    public static String filterSql(String sqlStr)
    {
        String[] strs = sqlStr.split(" ");
        for (String str : strs)
        {
            if (sqlPattern.matcher(str).matches() == true)
            {
                sqlStr = sqlStr.replaceAll(str, "");
            }
        }
        return sqlStr;
    }

    public static String replaceStrByMap(String self, Map<String, String> replaceMap)
    {
        Map<String, String> params = new HashMap<String, String>();
        if (replaceMap != null && replaceMap.size() > 0)
        {
            for (String i : replaceMap.keySet())
            {
                params.put(i, replaceSef(i, replaceMap.get(i)));
            }
            StrSubstitutor replaceDef = new StrSubstitutor(params);
            return replaceDef.replace(self);
        }
        return self;
    }

    private static String replaceSef(String self, String str)
    {
        str = StringUtils.replace(str, "${" + self + "}", "${#" + self + "}");
        return str;
    }
}
