package com.sdx.common.service;

import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class SdxConstants
{
	// 系统参数
	public static String WEB_CLASS_PATH;
	
	// BMS相关参数
	private static final String BMS_PREFIX = "bms.";
	public static String BMS_APP_ID = null;
	public static String BMS_APP_KEY = null;
	public static String BMS_URL = null;
	public static boolean useTestClient = true;
	
	// 连接相关配置
	private static final String CONSTANTS_PREFIX = "constants.http.";
	public static int CONNECTION_TIMEOUT = 6000;
	public static int SO_TIMEOUT = 10000;

	public static void loadConfig(Map<String, String> params)
	{
		// BMS相关初始化
		String temp = params.get(BMS_PREFIX + "appid");
		BMS_APP_ID = temp;
		temp = params.get(BMS_PREFIX + "appkey");
		BMS_APP_KEY = temp;
		temp = params.get(BMS_PREFIX + "url");
		BMS_URL = temp;
		temp = params.get(BMS_PREFIX + "useTestClient");
		useTestClient = BooleanUtils.toBooleanDefaultIfNull(BooleanUtils.toBooleanObject(temp), useTestClient);

		// 连接相关初始化
		temp = params.get(CONSTANTS_PREFIX + "connectionTimeout");
		CONNECTION_TIMEOUT = NumberUtils.toInt(temp, CONNECTION_TIMEOUT);
		temp = params.get(CONSTANTS_PREFIX + "soTimeout");
		SO_TIMEOUT = NumberUtils.toInt(temp, SO_TIMEOUT);
	}
}
