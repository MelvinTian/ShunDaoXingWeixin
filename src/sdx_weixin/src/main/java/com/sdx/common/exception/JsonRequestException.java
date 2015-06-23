package com.sdx.common.exception;

public class JsonRequestException extends CustomMsgException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4755179320193125824L;

	private String code;
	
	public JsonRequestException(String msg, String code)
	{
		super(msg);
		this.code = code;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
}