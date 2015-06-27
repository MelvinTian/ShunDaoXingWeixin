package com.sdx.bms.client;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sdx.common.exception.CustomMsgException;

@Component
public class BMSTestClient implements IBMSClient
{
	private static final Logger log = Logger.getLogger(BMSTestClient.class);
	
	private static final String TEST_DATA_PATH = "testJson/";
	
	public BMSTestClient()
	{
	}

	public JSONObject sendRequest(JSONObject content, BMSRequestFunc func) throws CustomMsgException
	{
		String fileContent = null;
		try
		{
			fileContent = FileUtils.readFileToString(new File(TEST_DATA_PATH + func.name() + ".json"));
		}
		catch (IOException e)
		{
			log.error(e.getMessage(), e);
		}
		return JSONObject.fromObject(fileContent);
	}
}