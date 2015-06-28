package com.sdx.weixin.client.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdx.bms.client.BMSEngine;
import com.sdx.common.exception.CustomMsgException;
import com.sdx.weixin.client.ClientException;
import com.sdx.weixin.client.ClientHandler;
import com.sdx.weixin.client.JsonBody;
import com.sdx.weixin.service.entity.OrderInfo;

@Service("AddUUIDService")
public class SearchCaseHandler extends ClientHandler
{
	private static final Logger log = Logger.getLogger(SearchCaseHandler.class);
	
	@Autowired
	private BMSEngine engine;
	
	@Override
	public void handlerRequest(JsonBody body, HttpServletRequest request) throws ClientException
	{
		//获取用户登录的账号
    	String keyword = body.getBody().optString("keyword");
    	try
		{
			List<OrderInfo> orderList = engine.searchOrder(keyword);
			body.getBody().put("orderList", orderList);
		}
		catch (CustomMsgException e)
		{
			log.error(e.getMessage(), e);
			throw new ClientException(e.getMessage(), e);
		}
	}
}