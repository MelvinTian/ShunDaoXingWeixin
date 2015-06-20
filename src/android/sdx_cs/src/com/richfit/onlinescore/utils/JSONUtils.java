/* 
 * Copyright (C) 2013,中油瑞飞, 
 * All Rights Reserved 
 * Description: JSONObject转对象
 * 
 * @project 数据中心运行管理系统
 * @author  田广文
 * @date    2014年5月20日-下午3:31:16
 *
 * 代码修改历史: 
 **********************************************************
 * 时间		       作者		          注释
 * 2014年5月20日	       Melvin		     	Create
 **********************************************************
 */

package com.richfit.onlinescore.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * JSONObject转对象
 * @author 田广文
 * @date 2014年5月20日-下午3:31:16
 */
public class JSONUtils
{
    public static String toJSONString(Object javaBean)
    {
        return new Gson().toJson(javaBean);
    }
    public static  Map<String, Object>   toMap(JSONObject object) throws JSONException
    {
    	Iterator  ir =object.keys();
    	Map<String, Object>  map  = new HashMap<String, Object>();
    	while (ir.hasNext())
		{
    		String key  = (String) ir.next();
    		String value =object.getString(key);
    		map.put(key, value);
		}
		return map;
    }
    public static JSONObject toJSONObject(Object javaBean)
    {
        try
        {
            return new JSONObject(toJSONString(javaBean));
        }
        catch (JSONException e)
        {
            return null;
        }
    }

    public static <T> T fromJSONString(String json, Class<T> cls)
    {
        return new Gson().fromJson(json, cls);
    }
}
