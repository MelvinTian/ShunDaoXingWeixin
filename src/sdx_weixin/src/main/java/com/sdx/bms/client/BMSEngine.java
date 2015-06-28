package com.sdx.bms.client;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdx.common.exception.CustomMsgException;
import com.sdx.common.exception.ErrorCodeConstants;
import com.sdx.common.service.SdxConstants;
import com.sdx.weixin.service.entity.CustomerInfo;

@Component
public class BMSEngine
{
	private static final Logger log = Logger.getLogger(BMSEngine.class);

	@Autowired
	private BMSTestClient testClient;

	@Autowired
	private BMSClient client;
	
	private IBMSClient getClient()
	{
		if (SdxConstants.useTestClient)
		{
			return testClient;
		}
		return client;
	}

	public CustomerInfo bindUser(String userMobile, String openId) throws CustomMsgException
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("USERID", userMobile);
		jsonObject.put("TOKENID", openId);
		JSONObject result = getClient().sendRequest(jsonObject, BMSRequestFunc.BindUser);
		CustomerInfo customerInfo = null;
		try
		{
			customerInfo = (CustomerInfo) JSONObject.toBean(result.optJSONObject("CUSTOMER"), CustomerInfo.class);
		} catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new CustomMsgException("JSON 解析异常", ErrorCodeConstants.DATA_FORMAT_ERROR, e);
		}
		return customerInfo;
	}
}