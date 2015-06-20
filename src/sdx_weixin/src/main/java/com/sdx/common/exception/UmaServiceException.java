package com.sdx.common.exception;

public class UmaServiceException  extends NestedUmaException  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4948541507887082254L;
	
	public UmaServiceException(String msg) {
		super(msg);
	}
	public UmaServiceException(String msg, Throwable ex){
		super(msg,ex);
	}
	
}
