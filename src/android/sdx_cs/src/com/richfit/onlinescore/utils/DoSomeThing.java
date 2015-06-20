/* 
* Copyright (C) 2013,中油瑞飞, 
* All Rights Reserved 
* Description: 执行接口
* 
* @project 数据中心运行管理系统
* @author  田广文
* @date    2014年5月20日-下午3:15:38
*
* 代码修改历史: 
**********************************************************
* 时间		       作者		          注释
* 2014年5月20日	       Melvin		     	Create
**********************************************************
*/
package com.richfit.onlinescore.utils;

/**
 * 执行接口
 * @author 田广文
 * @date   2014年5月20日-下午3:15:38
 *
 */
public interface DoSomeThing<E>
{
    public void doWith(E e);
}
