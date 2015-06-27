package com.sdx.weixin.service.entity;

public class BookInfo
{
	private String BOOKID;// 预约ID
	private String BOOKDATE;// 预约日期
	private String BTIME;// 开始时间
	private String ETIME;// 结束时间
	private int TOTALCOUNT;// 总共可预约数量
	private int BOOKEDCOUNT;// 已经预约数量

	public String getBOOKID()
	{
		return BOOKID;
	}

	public void setBOOKID(String bOOKID)
	{
		BOOKID = bOOKID;
	}

	public String getBOOKDATE()
	{
		return BOOKDATE;
	}

	public void setBOOKDATE(String bOOKDATE)
	{
		BOOKDATE = bOOKDATE;
	}

	public String getBTIME()
	{
		return BTIME;
	}

	public void setBTIME(String bTIME)
	{
		BTIME = bTIME;
	}

	public String getETIME()
	{
		return ETIME;
	}

	public void setETIME(String eTIME)
	{
		ETIME = eTIME;
	}

	public int getTOTALCOUNT()
	{
		return TOTALCOUNT;
	}

	public void setTOTALCOUNT(int tOTALCOUNT)
	{
		TOTALCOUNT = tOTALCOUNT;
	}

	public int getBOOKEDCOUNT()
	{
		return BOOKEDCOUNT;
	}

	public void setBOOKEDCOUNT(int bOOKEDCOUNT)
	{
		BOOKEDCOUNT = bOOKEDCOUNT;
	}
}
