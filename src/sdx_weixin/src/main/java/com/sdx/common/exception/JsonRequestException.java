package com.sdx.common.exception;

public class JsonRequestException extends CustomMsgException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4755179320193125824L;

	public JsonRequestException(String msg, String code)
	{
		super(msg, code);
	}
	
	public JsonRequestException(String msg, String code, Throwable t)
	{
		super(msg, code, t);
	}
}