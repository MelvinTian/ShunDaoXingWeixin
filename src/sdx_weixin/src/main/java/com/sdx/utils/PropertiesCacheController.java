package com.sdx.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author QI 属性文件缓存
 */
public abstract class PropertiesCacheController
{
	private static Map<String, String> basicData;

	public static Map<String, String> getBasicData()
	{
		if (basicData == null)
		{
			initBasicData();
		}
		return basicData;
	}

	public static void setBasicData(Map<String, String> basicData)
	{
		PropertiesCacheController.basicData = basicData;
	}

	public static void initBasicData()
	{
		Locale locale = Locale.getDefault();
		ResourceBundle localResource = ResourceBundle.getBundle("basicData", locale);
		Set<String> keys = localResource.keySet();
		HashMap<String, String> map = new HashMap<String, String>();
		for (String key : keys)
		{
			String vals = localResource.getString(key);
			map.put(key, vals);
		}
		basicData = map;
	}
}
