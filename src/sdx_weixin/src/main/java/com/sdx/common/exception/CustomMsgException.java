package com.sdx.common.exception;

public class CustomMsgException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1458791228837998981L;
	private String msg;
	private String code;

	public CustomMsgException(String msg, String code)
	{
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public CustomMsgException(String msg, String code, Throwable e)
	{
		super(e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg()
	{
		return this.msg;
	}
	
	public String getCode()
	{
		return this.code;
	}
}