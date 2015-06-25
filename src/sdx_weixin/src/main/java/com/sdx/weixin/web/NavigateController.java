package com.sdx.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sdx.common.exception.CustomMsgException;
import com.sdx.common.exception.PageRequestException;
import com.sdx.utils.PropertiesCacheController;
import com.sdx.utils.WeixinConstants;
import com.sdx.utils.web.HttpsClient;

@Controller
@RequestMapping(value = "/navigate")
public class NavigateController
{
	// 跳转页面前缀
	private static final String PREFIX = "redirect:";
	private static final String NAVIGATE_ITEM_PREFIX = "navigate.";
	
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";
//	private static final String ACCESS_TOKEN_URL = "https://app.richfit.com/html/test.json?grant_type=authorization_code";
	
	@RequestMapping(value="/{item}")
	public ModelAndView index(@PathVariable String item, HttpServletRequest request, HttpServletResponse response) throws PageRequestException
	{
		String code = request.getParameter("code");
		if (StringUtils.isEmpty(code))
		{
			throw new PageRequestException("请在微信客户端浏览该页面");
		}
		String forwardUrl = PropertiesCacheController.getBasicData().get(NAVIGATE_ITEM_PREFIX + item);
		if (StringUtils.isEmpty(forwardUrl))
		{
			throw new PageRequestException("跳转地址不合法");
		}
		StringBuffer accessTokenUri = new StringBuffer(ACCESS_TOKEN_URL);
		accessTokenUri.append("&appid=").append(WeixinConstants.weixinAppid()).append("&secret=").append(WeixinConstants.weixinSecret()).append("&code=").append(code);
		JSONObject resJson;
		try
		{
			resJson = JSONObject.fromObject(HttpsClient.doGet(accessTokenUri.toString()));
		}
		catch (CustomMsgException e)
		{
			throw new PageRequestException(e.getMsg());
		}
		String error = resJson.optString("errmsg");
		if (!StringUtils.isEmpty(error))
		{
			throw new PageRequestException(resJson.optString("errmsg"));
		}
		String openId = resJson.getString("openid");
		request.getSession().setAttribute("openId", openId);

		return new ModelAndView(PREFIX + item);
	}
}
