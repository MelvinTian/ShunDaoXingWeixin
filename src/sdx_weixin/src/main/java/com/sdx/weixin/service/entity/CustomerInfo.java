package com.sdx.weixin.service.entity;

import java.util.List;

public class CustomerInfo
{
	private String ACCOUNTID;// 会员ID
	private String ACCOUNTNO;// 会员号
	private Float BALANCE;// 会员当前余额
	private String ACCOUNTLEVEL;// 会员级别
	private String CURRENTPKGINFO;// 当前套餐名称
	private String CURRENTPKGDATE;// 当前套餐购买日期
	private List<CustServiceInfo> SERVICELIST;// 客户套餐服务项目明细

	public String getACCOUNTID()
	{
		return ACCOUNTID;
	}

	public void setACCOUNTID(String aCCOUNTID)
	{
		ACCOUNTID = aCCOUNTID;
	}

	public String getACCOUNTNO()
	{
		return ACCOUNTNO;
	}

	public void setACCOUNTNO(String aCCOUNTNO)
	{
		ACCOUNTNO = aCCOUNTNO;
	}

	public Float getBALANCE()
	{
		return BALANCE;
	}

	public void setBALANCE(Float bALANCE)
	{
		BALANCE = bALANCE;
	}

	public String getACCOUNTLEVEL()
	{
		return ACCOUNTLEVEL;
	}

	public void setACCOUNTLEVEL(String aCCOUNTLEVEL)
	{
		ACCOUNTLEVEL = aCCOUNTLEVEL;
	}

	public String getCURRENTPKGINFO()
	{
		return CURRENTPKGINFO;
	}

	public void setCURRENTPKGINFO(String cURRENTPKGINFO)
	{
		CURRENTPKGINFO = cURRENTPKGINFO;
	}

	public String getCURRENTPKGDATE()
	{
		return CURRENTPKGDATE;
	}

	public void setCURRENTPKGDATE(String cURRENTPKGDATE)
	{
		CURRENTPKGDATE = cURRENTPKGDATE;
	}

	public List<CustServiceInfo> getSERVICELIST()
	{
		return SERVICELIST;
	}

	public void setSERVICELIST(List<CustServiceInfo> sERVICELIST)
	{
		SERVICELIST = sERVICELIST;
	}

}
