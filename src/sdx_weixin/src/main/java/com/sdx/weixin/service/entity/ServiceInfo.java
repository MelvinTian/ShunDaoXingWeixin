package com.sdx.weixin.service.entity;


public class ServiceInfo
{
	private String SERVICEID;// 服务项目ID
	private String SERVICENAME;// 服务项目名称
	private String DESC;// 服务描述
	private Float PRICE;// 服务价格
	private String SERVICETYPE;// 服务方式

	public String getSERVICEID()
	{
		return SERVICEID;
	}

	public void setSERVICEID(String sERVICEID)
	{
		SERVICEID = sERVICEID;
	}

	public String getSERVICENAME()
	{
		return SERVICENAME;
	}

	public void setSERVICENAME(String sERVICENAME)
	{
		SERVICENAME = sERVICENAME;
	}

	public String getDESC()
	{
		return DESC;
	}

	public void setDESC(String dESC)
	{
		DESC = dESC;
	}

	public Float getPRICE()
	{
		return PRICE;
	}

	public void setPRICE(Float pRICE)
	{
		PRICE = pRICE;
	}

	public String getSERVICETYPE()
	{
		return SERVICETYPE;
	}

	public void setSERVICETYPE(String sERVICETYPE)
	{
		SERVICETYPE = sERVICETYPE;
	}

}
