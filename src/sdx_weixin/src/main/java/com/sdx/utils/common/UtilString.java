package com.sdx.utils.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings({"unchecked", "rawtypes"})
public class UtilString
{

    public UtilString()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        String s = encodeHex("\u4F60\u597D94", "gbk");
        System.out.println(s);
        System.out.println(decodeHex(s, "gbk"));
    }

    public static Map splitToMap(String s, char c, char c1)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        String as[] = StringUtils.split(s, c);
        String as1[] = as;
        int i = as1.length;
        for(int j = 0; j < i; j++)
        {
            String s1 = as1[j];
            int k = s1.indexOf(c1);
            if(k >= 0)
            {
                String s2 = escapeSql(s1.substring(0, k));
                String s3 = escapeSql(s1.substring(k + 1));
                linkedhashmap.put(StringUtils.strip(s2), StringUtils.strip(s3));
            }
        }

        return linkedhashmap;
    }

    public static List splitToList(String s, char c)
    {
        ArrayList arraylist = new ArrayList();
        StringBuilder stringbuilder = new StringBuilder();
        int i = s.length();
        for(int j = 0; j < i; j++)
        {
            char c1 = s.charAt(j);
            if(Character.isSpaceChar(c1))
                continue;
            if(c1 == c)
            {
                arraylist.add(stringbuilder.toString());
                stringbuilder.setLength(0);
            } else
            {
                stringbuilder.append(c1);
            }
        }

        if(stringbuilder.length() > 0)
            arraylist.add(stringbuilder.toString());
        return arraylist;
    }

    public static String escapeSql(String s)
    {
        return StringUtils.replace(s, "'", "''");
    }

    public static String unescapeSql(String s)
    {
        return StringUtils.replace(s, "''", "'");
    }

    public static String mapToString(Map map, char c, char c1)
    {
        if(MapUtils.isEmpty(map))
            return "";
        StringBuilder stringbuilder = new StringBuilder();
        java.util.Map.Entry entry;
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); stringbuilder.append((String)entry.getKey()).append(c1).append((String)entry.getValue()).append(c))
            entry = (java.util.Map.Entry)iterator.next();

        stringbuilder.setLength(stringbuilder.length() - 1);
        return stringbuilder.toString();
    }

    public static final String encodeHex(String s, String s1)
    {
        try
        {
            return new String(Hex.encodeHex(s.getBytes(s1), true));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new IllegalStateException((new StringBuilder()).append(s1).append(": ").append(unsupportedencodingexception).toString());
        }
    }

    public static final String decodeHex(String s, String s1)
    {
        try
        {
            return org.apache.commons.codec.binary.StringUtils.newString(Hex.decodeHex(s.toCharArray()), s1);
        }
        catch(DecoderException decoderexception)
        {
            throw new IllegalStateException((new StringBuilder()).append(s1).append(": ").append(decoderexception).toString());
        }
    }

    public static final String ereaseZeros(String s)
    {
        s = s.trim();
        int i = s.length();
        int j = s.length();
        for(; s.charAt(i - 1) == '0'; i--);
        for(; s.charAt(i - 1) == '.'; i--);
        return i >= j ? s : s.substring(0, i);
    }

    public static void convertUnderlineToUppercase(String s, StringBuilder stringbuilder)
    {
        try
        {
            stringbuilder.setLength(0);
            for(int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                if(c == '_')
                {
                    c = s.charAt(++i);
                    stringbuilder.append(Character.toUpperCase(c));
                } else
                {
                    stringbuilder.append(Character.toLowerCase(c));
                }
            }

        }
        catch(StringIndexOutOfBoundsException stringindexoutofboundsexception) { }
    }

    public static String toString(Object obj)
    {
        return obj != null ? String.valueOf(obj) : "";
    }

    public static String str(String as[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        String as1[] = as;
        int i = as1.length;
        for(int j = 0; j < i; j++)
        {
            String s = as1[j];
            stringbuilder.append(s);
        }

        return stringbuilder.toString();
    }

    public static String str(char c, String as[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        String as1[] = as;
        int i = as1.length;
        for(int j = 0; j < i; j++)
        {
            String s = as1[j];
            stringbuilder.append(s).append(c);
        }

        stringbuilder.setLength(stringbuilder.length() - 1);
        return stringbuilder.toString();
    }
    public static Map<String,String> changeRequestMap(HttpServletRequest request) {
		Map<String,String>  returnMap=new HashMap<String,String>();
		Map<String,String[]> map=request.getParameterMap();
		for(String key:map.keySet()){
			String[] item=map.get(key);
		     if(item.length>1){
		    	 String values=item[0];
		    	 for(int i=1;i<item.length;i++){
		    		 values=values+","+item[i];
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
    
}
