package com.sdx.weixin.service.entity;

public class OrderDetailInfo
{
	private String DETAILID;// 明细ID
	private String ORDERNO;// 服务订单号
	private ServiceInfo SERVICEITEM;// 服务项目
	private Float SERVICECOUNT;// 服务项目数量
	private Float AMOUNT;// 金额

	public String getDETAILID()
	{
		return DETAILID;
	}

	public void setDETAILID(String dETAILID)
	{
		DETAILID = dETAILID;
	}

	public String getORDERNO()
	{
		return ORDERNO;
	}

	public void setORDERNO(String oRDERNO)
	{
		ORDERNO = oRDERNO;
	}

	public ServiceInfo getSERVICEITEM()
	{
		return SERVICEITEM;
	}

	public void setSERVICEITEM(ServiceInfo sERVICEITEM)
	{
		SERVICEITEM = sERVICEITEM;
	}

	public Float getSERVICECOUNT()
	{
		return SERVICECOUNT;
	}

	public void setSERVICECOUNT(Float sERVICECOUNT)
	{
		SERVICECOUNT = sERVICECOUNT;
	}

	public Float getAMOUNT()
	{
		return AMOUNT;
	}

	public void setAMOUNT(Float aMOUNT)
	{
		AMOUNT = aMOUNT;
	}
}
