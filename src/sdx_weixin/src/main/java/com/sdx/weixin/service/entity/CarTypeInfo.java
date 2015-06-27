package com.sdx.weixin.service.entity;


public class CarTypeInfo
{
	private String CARTYPEID;// 车型ID
	private String FACTORYNO;// 车厂
	private String BRANDNAME;// 车辆品牌
	private String BRANDLOGO;// 品牌LOGO

	public String getCARTYPEID()
	{
		return CARTYPEID;
	}

	public void setCARTYPEID(String cARTYPEID)
	{
		CARTYPEID = cARTYPEID;
	}

	public String getFACTORYNO()
	{
		return FACTORYNO;
	}

	public void setFACTORYNO(String fACTORYNO)
	{
		FACTORYNO = fACTORYNO;
	}

	public String getBRANDNAME()
	{
		return BRANDNAME;
	}

	public void setBRANDNAME(String bRANDNAME)
	{
		BRANDNAME = bRANDNAME;
	}

	public String getBRANDLOGO()
	{
		return BRANDLOGO;
	}

	public void setBRANDLOGO(String bRANDLOGO)
	{
		BRANDLOGO = bRANDLOGO;
	}
}
