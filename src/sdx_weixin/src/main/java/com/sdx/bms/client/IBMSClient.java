package com.sdx.bms.client;

import net.sf.json.JSONObject;

import com.sdx.common.exception.CustomMsgException;

public interface IBMSClient
{
	public JSONObject sendRequest(JSONObject content, BMSRequestFunc func) throws CustomMsgException;
}