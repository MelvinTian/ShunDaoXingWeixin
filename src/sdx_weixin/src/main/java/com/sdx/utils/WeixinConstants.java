package com.sdx.utils;

public class WeixinConstants
{
	private static String WEIXIN_CONSTANTS_PREFIX = "constants.weixin.";

	public static String weixinAppid()
	{
		return PropertiesCacheController.getBasicData().get(WEIXIN_CONSTANTS_PREFIX + "appId");
	}
	public static String weixinSecret()
	{
		return PropertiesCacheController.getBasicData().get(WEIXIN_CONSTANTS_PREFIX + "secret");
	}
}
