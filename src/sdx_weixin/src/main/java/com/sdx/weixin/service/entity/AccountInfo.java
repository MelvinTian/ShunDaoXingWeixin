package com.sdx.weixin.service.entity;

import java.util.List;

public class AccountInfo
{
	private String CUSTOMERID;// 客户ID号
	private String CUSTOMERNAME;// 客户姓名
	private String SEX;// 性别
	private String EMAIL;// 客户Email
	private String MOBILENO;// 手机号码
	private String LICENSENO;// 驾驶证号码
	private String CURRENTPOINT;// 当前积分
	private int CHECKEDMOBILE;// 手机号码是否通过验证
	private int CHECKEDEMAIL;// Email是否通过验证
	private List<CarInfo> CARINFOLIST;// 车辆信息列表
	private AccountInfo ACCOUNTINFO;// 会员信息

	public String getCUSTOMERID()
	{
		return CUSTOMERID;
	}

	public void setCUSTOMERID(String cUSTOMERID)
	{
		CUSTOMERID = cUSTOMERID;
	}

	public String getCUSTOMERNAME()
	{
		return CUSTOMERNAME;
	}

	public void setCUSTOMERNAME(String cUSTOMERNAME)
	{
		CUSTOMERNAME = cUSTOMERNAME;
	}

	public String getSEX()
	{
		return SEX;
	}

	public void setSEX(String sEX)
	{
		SEX = sEX;
	}

	public String getEMAIL()
	{
		return EMAIL;
	}

	public void setEMAIL(String eMAIL)
	{
		EMAIL = eMAIL;
	}

	public String getMOBILENO()
	{
		return MOBILENO;
	}

	public void setMOBILENO(String mOBILENO)
	{
		MOBILENO = mOBILENO;
	}

	public String getLICENSENO()
	{
		return LICENSENO;
	}

	public void setLICENSENO(String lICENSENO)
	{
		LICENSENO = lICENSENO;
	}

	public String getCURRENTPOINT()
	{
		return CURRENTPOINT;
	}

	public void setCURRENTPOINT(String cURRENTPOINT)
	{
		CURRENTPOINT = cURRENTPOINT;
	}

	public int getCHECKEDMOBILE()
	{
		return CHECKEDMOBILE;
	}

	public void setCHECKEDMOBILE(int cHECKEDMOBILE)
	{
		CHECKEDMOBILE = cHECKEDMOBILE;
	}

	public int getCHECKEDEMAIL()
	{
		return CHECKEDEMAIL;
	}

	public void setCHECKEDEMAIL(int cHECKEDEMAIL)
	{
		CHECKEDEMAIL = cHECKEDEMAIL;
	}

	public List<CarInfo> getCARINFOLIST()
	{
		return CARINFOLIST;
	}

	public void setCARINFOLIST(List<CarInfo> cARINFOLIST)
	{
		CARINFOLIST = cARINFOLIST;
	}

	public AccountInfo getACCOUNTINFO()
	{
		return ACCOUNTINFO;
	}

	public void setACCOUNTINFO(AccountInfo aCCOUNTINFO)
	{
		ACCOUNTINFO = aCCOUNTINFO;
	}
}
