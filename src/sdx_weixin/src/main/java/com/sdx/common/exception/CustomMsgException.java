package com.sdx.common.exception;

public class CustomMsgException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1458791228837998981L;
	private String msg;

	public CustomMsgException(String msg)
	{
		super(msg);
		this.msg = msg;
	}
	
	public CustomMsgException(String msg, Throwable e)
	{
		super(e);
		this.msg = msg;
	}

	public String getMsg()
	{
		return this.msg;
	}
}