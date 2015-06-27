package com.sdx.weixin.service.entity;

public class PaymentInfo
{
	private String PAYID;// 支付流水号
	private Float PAYAMOUNT;// 支付金额
	private String PAYTYPE;// 支付方式
	private String PAYACCOUNTNO;// 支付账号信息
	private String ORDERID;// 对应订单ID号。
	private String PAYTIME;// 支付时间

	public String getPAYID()
	{
		return PAYID;
	}

	public void setPAYID(String pAYID)
	{
		PAYID = pAYID;
	}

	public Float getPAYAMOUNT()
	{
		return PAYAMOUNT;
	}

	public void setPAYAMOUNT(Float pAYAMOUNT)
	{
		PAYAMOUNT = pAYAMOUNT;
	}

	public String getPAYTYPE()
	{
		return PAYTYPE;
	}

	public void setPAYTYPE(String pAYTYPE)
	{
		PAYTYPE = pAYTYPE;
	}

	public String getPAYACCOUNTNO()
	{
		return PAYACCOUNTNO;
	}

	public void setPAYACCOUNTNO(String pAYACCOUNTNO)
	{
		PAYACCOUNTNO = pAYACCOUNTNO;
	}

	public String getORDERID()
	{
		return ORDERID;
	}

	public void setORDERID(String oRDERID)
	{
		ORDERID = oRDERID;
	}

	public String getPAYTIME()
	{
		return PAYTIME;
	}

	public void setPAYTIME(String pAYTIME)
	{
		PAYTIME = pAYTIME;
	}
}
