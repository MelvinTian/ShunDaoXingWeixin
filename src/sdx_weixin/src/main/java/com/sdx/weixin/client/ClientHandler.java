/* 
* Copyright (C) 2013,中油瑞飞, 
* All Rights Reserved 
* Description: 移动端处理接口
* 
* @project 数据中心运行管理系统
* @author  田广文
* @date    2014年5月19日-下午3:14:23
*
* 代码修改历史: 
**********************************************************
* 时间		       作者		          注释
* 2014年5月19日	       Melvin		     	Create
**********************************************************
*/
package com.sdx.weixin.client;

import javax.servlet.http.HttpServletRequest;

/**
 * 移动端处理接口，定义所有移动端处理类的通用方法
 * @author 田广文
 * @date   2014年5月19日-下午3:14:23
 *
 */
public abstract class ClientHandler
{
    public abstract void handlerRequest(JsonBody body, HttpServletRequest request) throws ClientException;

    protected void returnErrMsg(String msg) throws ClientException
    {
        throw new ClientException(msg);
    }
}
