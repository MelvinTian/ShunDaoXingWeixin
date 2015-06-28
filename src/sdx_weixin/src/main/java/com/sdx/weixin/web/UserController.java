package com.sdx.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdx.bms.client.BMSEngine;
import com.sdx.common.exception.CustomMsgException;
import com.sdx.common.exception.ErrorCodeConstants;
import com.sdx.common.exception.JsonRequestException;
import com.sdx.common.exception.PageRequestException;
import com.sdx.utils.JSONUtil;
import com.sdx.weixin.service.IUserService;
import com.sdx.weixin.service.entity.CustomerInfo;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
	@Autowired
	private BMSEngine engine;

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/bind/{weixinId}", produces = "json/application;charset=UTF-8")
	@ResponseBody
	public String bind(@PathVariable String weixinId, HttpServletRequest request, HttpServletResponse response) throws JsonRequestException
	{
		String userMobile = request.getParameter("userMobile");
		CustomerInfo customerInfo = null;
		try
		{
			customerInfo = engine.bindUser(userMobile, weixinId);
		}
		catch (CustomMsgException e)
		{
			throw new JsonRequestException(e.getMessage(), e.getCode(), e);
		}
		if (customerInfo == null)
		{
			throw new JsonRequestException("没有找到该用户", ErrorCodeConstants.USER_NOT_FOUND_ERROR);
		}
		String userId = customerInfo.getCUSTOMERID();
		userService.bindUser(userId, weixinId);
		JSONObject res = JSONUtil.createResultJSON();
		res.put("userId", userId);
		res.put("weixinId", weixinId);
		return res.toString();
	}

	@RequestMapping(value="/")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws PageRequestException
	{
		return new ModelAndView("user/bindUser");
	}
}
