package com.sdx.bms.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdx.common.entity.MiddLog;
import com.sdx.common.exception.CustomMsgException;
import com.sdx.common.mapper.MiddLogMapper;
import com.sdx.utils.security.Digests;
import com.sdx.utils.web.SdxHttpClient;

@Component
public class BMSClient implements IBMSClient
{
	@Autowired
	private MiddLogMapper middLogMapper;
	
	private static final Logger log = Logger.getLogger(BMSClient.class);
	
	public BMSClient()
	{
	}

	private Random random = new Random(System.currentTimeMillis());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
	
	private static final String BMS_PREFIX = "bms.";

	private static String BMS_APP_ID = null;
	private static String BMS_APP_KEY = null;
	private static String BMS_URL = null;

	public static void configClient(Map<String, String> params)
	{
		String temp = params.get(BMS_PREFIX + ".appid");
		BMS_APP_ID = temp;
		temp = params.get(BMS_PREFIX + ".appkey");
		BMS_APP_KEY = temp;
		temp = params.get(BMS_PREFIX + ".url");
		BMS_URL = temp;
	}

	private String getRequestBody(JSONObject content, BMSRequestFunc func, MiddLog mLog)
	{
		JSONObject header = new JSONObject();
		header.put("VERSION", "V1.0");
		header.put("APPID", BMS_APP_ID);
		String timeStamp = sdf.format(new Date(System.currentTimeMillis()));
		header.put("TIMESTAMP", timeStamp);
		String seqNo = timeStamp + StringUtils.leftPad(random.nextInt() + "", 4, '0');
		header.put("SEQNO", seqNo);

		String secert = timeStamp + seqNo + BMS_APP_ID + BMS_APP_KEY;
		header.put("SECERTKEY", Digests.md5(secert.getBytes()));

		JSONObject body = new JSONObject();
		body.put("CONTENT", content);
		
		JSONObject req = new JSONObject();
		req.put("FUNCID", func.name());
		body.put("REQ", req);
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("HEADER", header);
		requestBody.put("MSGBODY", body);

		String requestContent = requestBody.toString();
		
		mLog.setReqno(seqNo);
		mLog.setContent(requestContent);
		mLog.setCreateTile(new Date(System.currentTimeMillis()));
		mLog.setUrl(BMS_URL + func.uri());
		middLogMapper.insert(mLog);

		return requestContent;
	}

	public JSONObject sendRequest(JSONObject content, BMSRequestFunc func) throws CustomMsgException
	{
		MiddLog mLog = new MiddLog();
		String requestContent = getRequestBody(content, func, mLog);
		HttpResponse response = SdxHttpClient.sendRequest(BMS_URL + func.uri(), requestContent);
		String responseStr = null;
		try
		{
			responseStr = EntityUtils.toString(response.getEntity());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new CustomMsgException("返回数据内容错误");
		}
		mLog.setResult(responseStr);
		middLogMapper.updateByPrimaryKey(mLog);
		JSONObject responseObj = JSONObject.fromObject(responseStr);
		JSONObject msgBody = responseObj.optJSONObject("MSGBODY");
		if (msgBody != null)
		{
			JSONObject resp = msgBody.optJSONObject("RESP");
			String rcode = resp.optString("RCODE");
			if (rcode == null || "00".equals(rcode))
			{
				return msgBody.optJSONObject("CONTENT");
			} else {
				log.error("获取BMS接口错误 : " + responseStr);
				throw new CustomMsgException(resp.optString("RMSG"));
			}
		}
		log.error("获取BMS接口错误 : " + responseStr);
		throw new CustomMsgException("服务器返回格式不正确");
	}
}