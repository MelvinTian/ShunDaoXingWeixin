package com.sdx.bms.client;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.sdx.common.exception.CustomMsgException;
import com.sdx.utils.JSONUtil;

@Component
public class BMSTestClient implements IBMSClient
{	
	public BMSTestClient()
	{
	}

	public JSONObject sendRequest(JSONObject content, BMSRequestFunc func) throws CustomMsgException
	{
		return JSONUtil.readTestJson(func.name());
	}
}