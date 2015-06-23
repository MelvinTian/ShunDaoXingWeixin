package com.sdx.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdx.weixin.service.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/bind/{weixinId}", produces = "json/application;charset=UTF-8")
	@ResponseBody
	public String index(@PathVariable String weixinId, HttpServletRequest request, HttpServletResponse response)
	{
		String userId = request.getParameter("uid");
		JSONObject res = new JSONObject();
		res.put("userId", userId);
		res.put("weixinId", weixinId);
		userService.bindUser(userId, weixinId);
		return res.toString();
	}
}
