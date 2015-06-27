package com.sdx.weixin.service.entity;

public class CarInfo
{
	private String CARID;// 车辆ID
	private String VECHNO;// 车牌号码
	private Float CURRENTMILES;// 当前公里数
	private CarTypeInfo CARTYPE;// 车型
	private String ENGINEENO;// 发动机号
	private String CARNO;// 车架号

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

	public Float getCURRENTMILES()
	{
		return CURRENTMILES;
	}

	public void setCURRENTMILES(Float cURRENTMILES)
	{
		CURRENTMILES = cURRENTMILES;
	}

	public CarTypeInfo getCARTYPE()
	{
		return CARTYPE;
	}

	public void setCARTYPE(CarTypeInfo cARTYPE)
	{
		CARTYPE = cARTYPE;
	}

	public String getENGINEENO()
	{
		return ENGINEENO;
	}

	public void setENGINEENO(String eNGINEENO)
	{
		ENGINEENO = eNGINEENO;
	}

	public String getCARNO()
	{
		return CARNO;
	}

	public void setCARNO(String cARNO)
	{
		CARNO = cARNO;
	}
}
