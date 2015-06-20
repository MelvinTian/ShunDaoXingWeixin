
package com.sdx.utils;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.StringTypeHandler;

/**
 * 处理mysql数据库中字符集的问题
 * @author 田广文
 * @date 2014年5月30日-上午11:44:42
 */
public class EncodingTypeHandler extends StringTypeHandler
{
	private static final Log logger = LogFactory.getLog(EncodingTypeHandler.class);

	public EncodingTypeHandler()
	{
		logger.debug("init EncodingTypeHandler");
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#setParameter(java.sql.
	 * PreparedStatement, int, java.lang.Object,
	 * org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setParameter(PreparedStatement arg0, int arg1, String arg2, JdbcType arg3) throws SQLException
	{
		if (StringUtils.isNotBlank(arg2))
		{
			try
			{
				arg0.setBytes(arg1, arg2.getBytes("utf-8"));
			}
			catch (UnsupportedEncodingException e)
			{
				super.setParameter(arg0, arg1, arg2, arg3);
			}
		}
		else
		{
			super.setParameter(arg0, arg1, arg2, arg3);
		}
	}
}
