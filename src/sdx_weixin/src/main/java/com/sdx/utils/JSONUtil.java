
package com.sdx.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.sdx.common.exception.CustomMsgException;
import com.sdx.common.exception.ErrorCodeConstants;
import com.sdx.common.service.SdxConstants;
import com.sdx.utils.page.Page;
import com.sdx.utils.page.PageInfo;

/**
 * @author QI
 */
public abstract class JSONUtil
{
	private static final Logger log = Logger.getLogger(JSONUtil.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String TEST_DATA_PATH = "/testJson/";
	private static final String DEFAULT_CHARSET = "UTF-8";

    public static String getJSONObjectKey(String jsonStr)
    {
        JSONObject o = JSONObject.fromObject(jsonStr);
        return o.keySet().toArray()[0].toString();
    }
    
    public static String getRequestJSON(Map<String,String> map){
    	return JSONObject.fromObject(map).toString();
    }

    public static String getJSONObjectByKey(String jsonStr, String key)
    {
        JSONObject o = JSONObject.fromObject(jsonStr);
        return o.get(key).toString();
    }

    public static Object[] getJSONArrayObject(String jsonStr, String key)
    {
        JSONObject o = JSONObject.fromObject(jsonStr);
        JSONArray array = o.getJSONArray(key);
        return array.toArray();
    }

    public static String getJSONObject(String jsonStr, String key, String element)
    {
        JSONObject o = JSONObject.fromObject(jsonStr);
        JSONObject g = o.getJSONObject(key);
        return g.getString(element);
    }

    private static final JsonConfig jsonConfig;
    static
    {
        jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor()
        {
            @SuppressWarnings("unchecked")
            @Override
            public Object processArrayValue(Object arg0, JsonConfig arg1)
            {
                StringBuffer sb = new StringBuffer();
                if (arg0 instanceof Iterable)
                {
                    for (Date d : (Iterable<Date>) arg0)
                    {
                        sb.append(sdf.format(d));
                    }
                    if (sb.length() > 1)
                        sb.deleteCharAt(sb.length() - 1);
                }
                if (arg0 != null)
                    return arg0.toString();
                return sb.toString();
            }

            @Override
            public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2)
            {
                if (arg1 != null)
                {
                    if (arg1 instanceof Date)
                        return sdf.format((Date) arg1);
                    return arg1.toString();
                }
                return "";
            }
        });
    }
    public static String outputJSONDataByList(PageInfo pageview) {
		JSONObject obj = new JSONObject();
		obj.put("page", pageview.getPage()); // 当前页
		obj.put("total", pageview.getTotalPage()); // 总页数
		obj.put("records", pageview.getTotalResult()); // 总记录数
		obj.put("rows", pageview.getResultsList()); // 具体的Table显示内容
		return obj.toString();
	}
    public static final JSONObject parseJSONObject(Object obj)
    {
        return JSONObject.fromObject(obj, jsonConfig);
    }

    public static final JSONArray parseJSONArray(Object obj)
    {
        return JSONArray.fromObject(obj, jsonConfig);
    }
    /**
	 * 输出通用分页Json
	 * 
	 * @param pageview
	 * @return String
	 */
	public static String outputJSONData(Page pageview) {
		JSONObject obj = new JSONObject();
		obj.put("page", pageview.getPageNo()); // 当前页
		obj.put("total", pageview.getTotalPageCount()); // 总页数
		obj.put("records", pageview.getTotalCount()); // 总记录数
		JSONArray jsonArray = new JSONArray();

		List<Map<String, Object>> listMap = pageview.getResults();
		if (listMap != null)
		{
    		for (int i = 0; i < listMap.size(); i++) {
    			Map<String, Object> map = listMap.get(i);// 获得一列数据
    			JSONObject o = new JSONObject();
    			Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) { // 循环添加对应列名及数据
                    String key = iterator.next();
                    Object value = map.get(key);
                    if (value instanceof java.util.Date) {
                        DateFormat dateFormat = new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss");
                        o.put(key,
                                map.get(key) == null ? "" : dateFormat
                                        .format(value));
                    } else {
                        o.put(key, map.get(key) == null ? "" : map.get(key)
                                .toString());
                    }
    
                }
    			jsonArray.add(o);
    		}
		}
		obj.put("rows", jsonArray); // 具体的Table显示内容
		return obj.toString();

	}
	
	public static String outputJSONTree(List<Tree> treeList) throws Exception {
		List<Map<String, Object>> jsonMap = new ArrayList<Map<String, Object>>();
		for (Tree t : treeList) {
			Map<String, Object> mm = new HashMap<String, Object>();
			Field[] ff = t.getClass().getDeclaredFields();
			for (int i = 0; i < ff.length; i++) {
				if (!ff[i].getName().equals("treeExtend")) {
					if (ff[i].getGenericType().toString().equals("class java.lang.String")) {
						Method m = t.getClass().getMethod("get" + getMethodName(ff[i].getName()));
						String val = (String) m.invoke(t);
						mm.put(ff[i].getName(), val);
					}
					if (ff[i].getGenericType().toString().equals("class java.lang.Boolean")) {
						Method m = t.getClass().getMethod("get" + getMethodName(ff[i].getName()));
						Boolean val = (Boolean) m.invoke(t);
						mm.put(ff[i].getName(), val);
					}
				}
			}
			mm.put("children", t.getChildren());
			if (!t.getTreeExtend().isEmpty()) {
				for (Map.Entry<String, Object> m : t.getTreeExtend().entrySet()) {
					mm.put(m.getKey(), m.getValue());
				}
			}
			jsonMap.add(mm);
		}
		return JSONUtil.outPutJSONData(jsonMap);
	}
	
	/**
	 * 获取方法名
	 * 
	 * @param fildeName
	 * @return
	 * @throws Exception
	 * @author 张昭
	 */
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
	
	/**
	 * 输出区分数据类型json数组
	 * 
	 * @param listMap
	 * @return String
	 */
	public static String outPutJSONData(List<Map<String, Object>> listMap) {
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < listMap.size(); i++) {
			Map<String, Object> map = listMap.get(i);// 获得一列数据
			JSONObject o = new JSONObject();
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) { // 循环添加对应列名及数据
				String key = iterator.next();
				if (key.toUpperCase().equals("ID")||key.toUpperCase().equals("OFFICER")) {
					o.put(key, map.get(key) == null ? "" : map.get(key).toString());
				} else {
					o.put(key, map.get(key) == null ? "" : map.get(key));
				}
			}
			jsonArray.add(o);
		}
		return jsonArray.toString();
	}
	
	public static JSONObject createResultJSON()
	{
		JSONObject result = new JSONObject();
		return result;
	}
	
	public static JSONObject readTestJson(String fileName) throws CustomMsgException
	{
		String fileContent = null;
		File file = new File(SdxConstants.WEB_CLASS_PATH + TEST_DATA_PATH + fileName + ".json");
		try
		{
			fileContent = FileUtils.readFileToString(file, DEFAULT_CHARSET);
		}
		catch (IOException e)
		{
			log.error(e.getMessage(), e);
			log.error("file name error : " + file.getAbsolutePath());
			throw new CustomMsgException("读取测试协议结果错误", ErrorCodeConstants.CONNECTION_ERROR);
		}
		return JSONObject.fromObject(fileContent);
	}
}