package com.sdx.weixin.service.entity;

public class CustServiceInfo
{
	private String CUSTSERVID;// 序号
	private String PID;// 服务ID
	private String PNAME;// 服务名称
	private int TOTALAMOUNT;// 总共次数
	private int USEDAMOUNT;// 已用次数

	public String getCUSTSERVID()
	{
		return CUSTSERVID;
	}

	public void setCUSTSERVID(String cUSTSERVID)
	{
		CUSTSERVID = cUSTSERVID;
	}

	public String getPID()
	{
		return PID;
	}

	public void setPID(String pID)
	{
		PID = pID;
	}

	public String getPNAME()
	{
		return PNAME;
	}

	public void setPNAME(String pNAME)
	{
		PNAME = pNAME;
	}

	public int getTOTALAMOUNT()
	{
		return TOTALAMOUNT;
	}

	public void setTOTALAMOUNT(int tOTALAMOUNT)
	{
		TOTALAMOUNT = tOTALAMOUNT;
	}

	public int getUSEDAMOUNT()
	{
		return USEDAMOUNT;
	}

	public void setUSEDAMOUNT(int uSEDAMOUNT)
	{
		USEDAMOUNT = uSEDAMOUNT;
	}

}
