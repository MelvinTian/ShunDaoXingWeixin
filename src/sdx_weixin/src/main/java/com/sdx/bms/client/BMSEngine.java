package com.sdx.bms.client;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdx.common.exception.CustomMsgException;
import com.sdx.common.exception.ErrorCodeConstants;
import com.sdx.common.service.SdxConstants;
import com.sdx.weixin.service.entity.CustomerInfo;
import com.sdx.weixin.service.entity.OrderInfo;

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
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new CustomMsgException("JSON 解析异常", ErrorCodeConstants.DATA_FORMAT_ERROR, e);
		}
		return customerInfo;
	}

	public List<OrderInfo> searchOrder(String keyword) throws CustomMsgException
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keyword);
		try
		{
			JSONObject result = getClient().sendRequest(jsonObject, BMSRequestFunc.getOrderList);
			JSONArray orderList = result.optJSONArray("ORDERLIST");
			LinkedList<OrderInfo> list = new LinkedList<OrderInfo>();
			if (orderList != null && orderList.size() > 0)
			{
				for (int i = 0; i < orderList.size(); i++)
				{
					JSONObject json = orderList.optJSONObject(i);
					try
					{
						list.add((OrderInfo) JSONObject.toBean(json, OrderInfo.class));
					} catch (Exception e)
					{
						log.fatal(e.getMessage(), e);
					}
				}
			}
			return list;
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new CustomMsgException("返回数据异常", ErrorCodeConstants.DATA_FORMAT_ERROR, e);
		}
	}
}