package com.sdx.weixin.service.entity;

public class BookOrderInfo
{
	private String BOOKORDERID;// 预约订单号
	private String BOOKORDERNO;// 预约号
	private String CUSTOMERID;// 客户ID
	private String CARID;// 车辆ID
	private String VECHNO;// 车牌号码
	private String BOOKDATE;// 预约日期
	private String BOOKTIME;// 预约时段
	private ServiceInfo SERVICEITEM;// 预约服务
	private String SERVICETYPE;// 服务方式
	private String REMARK;// 备注
	private String STATE;// 预约状态

	public String getBOOKORDERID()
	{
		return BOOKORDERID;
	}

	public void setBOOKORDERID(String bOOKORDERID)
	{
		BOOKORDERID = bOOKORDERID;
	}

	public String getBOOKORDERNO()
	{
		return BOOKORDERNO;
	}

	public void setBOOKORDERNO(String bOOKORDERNO)
	{
		BOOKORDERNO = bOOKORDERNO;
	}

	public String getCUSTOMERID()
	{
		return CUSTOMERID;
	}

	public void setCUSTOMERID(String cUSTOMERID)
	{
		CUSTOMERID = cUSTOMERID;
	}

	public String getCARID()
	{
		return CARID;
	}

	public void setCARID(String cARID)
	{
		CARID = cARID;
	}

	public String getVECHNO()
	{
		return VECHNO;
	}

	public void setVECHNO(String vECHNO)
	{
		VECHNO = vECHNO;
	}

	public String getBOOKDATE()
	{
		return BOOKDATE;
	}

	public void setBOOKDATE(String bOOKDATE)
	{
		BOOKDATE = bOOKDATE;
	}

	public String getBOOKTIME()
	{
		return BOOKTIME;
	}

	public void setBOOKTIME(String bOOKTIME)
	{
		BOOKTIME = bOOKTIME;
	}

	public ServiceInfo getSERVICEITEM()
	{
		return SERVICEITEM;
	}

	public void setSERVICEITEM(ServiceInfo sERVICEITEM)
	{
		SERVICEITEM = sERVICEITEM;
	}

	public String getSERVICETYPE()
	{
		return SERVICETYPE;
	}

	public void setSERVICETYPE(String sERVICETYPE)
	{
		SERVICETYPE = sERVICETYPE;
	}

	public String getREMARK()
	{
		return REMARK;
	}

	public void setREMARK(String rEMARK)
	{
		REMARK = rEMARK;
	}

	public String getSTATE()
	{
		return STATE;
	}

	public void setSTATE(String sTATE)
	{
		STATE = sTATE;
	}
}
