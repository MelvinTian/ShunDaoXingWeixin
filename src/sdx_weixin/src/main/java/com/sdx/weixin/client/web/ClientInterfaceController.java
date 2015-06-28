/* 
 * Copyright (C) 2013,中油瑞飞, 
 * All Rights Reserved 
 * Description: 移动端业务接口类
 * 
 * @project 数据中心运行管理系统
 * @author  田广文
 * @date    2014年5月19日-下午2:57:50
 *
 * 代码修改历史: 
 **********************************************************
 * 时间		       作者		          注释
 * 2014年5月19日	       Melvin		     	Create
 **********************************************************
 */

package com.sdx.weixin.client.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoaderListener;

import com.sdx.common.entity.ClientLog;
import com.sdx.common.mapper.ClientLogMapper;
import com.sdx.utils.JSONUtil;
import com.sdx.weixin.client.ClientException;
import com.sdx.weixin.client.ClientHandler;
import com.sdx.weixin.client.JsonBody;

/**
 * 移动端业务接口类
 * @author 田广文
 * @date 2014年5月19日-下午2:57:50
 */
@Controller
@RequestMapping(value="client")
public class ClientInterfaceController
{
	private static final Logger logger = Logger.getLogger(ClientInterfaceController.class);
    
	@Autowired
	private ClientLogMapper requestLogMapper;

    @RequestMapping(value = "clientService")
    public void clientService(HttpServletRequest request, HttpServletResponse response)
    {
    	logger.info("Client request start!");
        String clientType = request.getHeader("clientType");
        int iClientType = "android".equalsIgnoreCase(clientType) ? 1 : 0;
        if (iClientType == 0)
        {
            iClientType = "iPhone".equalsIgnoreCase(clientType) ? 2 : 0;
        }
        if (iClientType == 0)
        {
            iClientType = "iPad".equalsIgnoreCase(clientType) ? 3 : 0;
        }
        boolean isCompress = BooleanUtils.toBoolean(request.getHeader("isCompress"));
        boolean isEncript = BooleanUtils.toBoolean(request.getHeader("isEncrypt"));
        JsonBody body = new JsonBody(iClientType, isCompress, isEncript);
        String contentData = request.getParameter("data");
        StringBuffer content = new StringBuffer(contentData == null ? "" : contentData);
        try
        {
            if (StringUtils.isBlank(contentData))
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
                String temp = null;
                while ((temp = reader.readLine()) != null)
                {
                    content.append(temp);
                }
                IOUtils.closeQuietly(reader);
            }
            body.setContent(content.toString());
        }
        catch (Exception e)
        {
            body.getHeader().setStatus(1).setDesc("读取请求错误");
            sendResponse(body, response, content);
            return;
        }
        String handlerName = body.getHeader().getType();
        ClientHandler handler = ContextLoaderListener.getCurrentWebApplicationContext().getBean(handlerName, ClientHandler.class);
        ///开始    去除用户名中@cnpc.com.cn后缀 2015-3-30
        if (body.getBody().get("account")!=null){
        	String account = body.getBody().optString("account");
        	body.getBody().put("account", account.replaceAll("@cnpc.com.cn", ""));
        }
        ///结束
        try
        {
            handler.handlerRequest(body, request);
        }
        catch (ClientException e)
        {
            if (e.getException() != null)
                logger.error(e.getMsg(), e.getException());
            body.getHeader().setStatus(1).setDesc(e.getMsg());
        }
        logger.info(body.getBody());
        sendResponse(body, response, content);
        logger.info("Client request end!");
    }
    /**
     * 封装响应方法，其中有日志的填写
     * @param body 
     * @param response
     * @param request
     */
    private void sendResponse(JsonBody body, HttpServletResponse response, StringBuffer request)
    {
        PrintWriter writer;
        try
        {
            response.setContentType("text/json; charset=utf-8");
            writer = response.getWriter();
            ClientLog log = new ClientLog();
            log.setClientType(body.getHeader().getClientType() + "");
            log.setRequestTime(new Date());
            log.setRequestContent(request.toString());
            JSONObject res = JSONUtil.parseJSONObject(body);
            log.setResposneContent(res.toString());
            requestLogMapper.insert(log);
            writer.print(res.toString());
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "testV1")
    public void test(HttpServletRequest request, HttpServletResponse response)
    {
        PrintWriter writer;
        try
        {
            writer = response.getWriter();
            writer.print("test");
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
        }
    }
}
