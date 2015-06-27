package com.sdx.weixin.service.entity;

import java.util.List;

public class ServiceDetailInfo
{
	private String OPRID;// 施工明细ID
	private ServiceInfo SERVICEITEM;// 服务项目
	private String NODENO;// 服务节点序号
	private String NODENAME;// 服务节点名称
	private Float BTIME;// 开始时间
	private String ETIME;// 结束时间
	private List<String> PICS;// 施工图片URL
	private String OPERATOR;// 施工人员

	public String getOPRID()
	{
		return OPRID;
	}

	public void setOPRID(String oPRID)
	{
		OPRID = oPRID;
	}

	public ServiceInfo getSERVICEITEM()
	{
		return SERVICEITEM;
	}

	public void setSERVICEITEM(ServiceInfo sERVICEITEM)
	{
		SERVICEITEM = sERVICEITEM;
	}

	public String getNODENO()
	{
		return NODENO;
	}

	public void setNODENO(String nODENO)
	{
		NODENO = nODENO;
	}

	public String getNODENAME()
	{
		return NODENAME;
	}

	public void setNODENAME(String nODENAME)
	{
		NODENAME = nODENAME;
	}

	public Float getBTIME()
	{
		return BTIME;
	}

	public void setBTIME(Float bTIME)
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

	public List<String> getPICS()
	{
		return PICS;
	}

	public void setPICS(List<String> pICS)
	{
		PICS = pICS;
	}

	public String getOPERATOR()
	{
		return OPERATOR;
	}

	public void setOPERATOR(String oPERATOR)
	{
		OPERATOR = oPERATOR;
	}

	public String getOPRDESC()
	{
		return OPRDESC;
	}

	public void setOPRDESC(String oPRDESC)
	{
		OPRDESC = oPRDESC;
	}

	private String OPRDESC;// 简要描述
}
