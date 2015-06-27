package com.sdx.weixin.service.entity;

import java.util.List;

public class OrderInfo
{
	private String ORDERID;// 订单ID
	private String ORDERNO;// 服务订单号
	private String CUSTOMERID;// 客户ID
	private String CARID;// 车辆ID
	private String VECHNO;// 车牌号码
	private String SERVICEDATE;// 服务日期
	private String BOOKORDERID;// 服务预约订单ID
	private List<OrderDetailInfo> ORDERDETAILS;// 服务项目列表
	private Float AMOUNT;// 订单金额
	private Float PAYAMOUNT;// 已付金额
	private List<PaymentInfo> PAYINFOLIST;// 支付信息明细
	private String CHECKOUTDATE;// 结算日期
	private String REMARK;// 备注
	private List<CommentInfo> COMMENTS;// 订单评论信息
	private String STATE;// 服务状态

	public String getORDERID()
	{
		return ORDERID;
	}

	public void setORDERID(String oRDERID)
	{
		ORDERID = oRDERID;
	}

	public String getORDERNO()
	{
		return ORDERNO;
	}

	public void setORDERNO(String oRDERNO)
	{
		ORDERNO = oRDERNO;
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

	public String getSERVICEDATE()
	{
		return SERVICEDATE;
	}

	public void setSERVICEDATE(String sERVICEDATE)
	{
		SERVICEDATE = sERVICEDATE;
	}

	public String getBOOKORDERID()
	{
		return BOOKORDERID;
	}

	public void setBOOKORDERID(String bOOKORDERID)
	{
		BOOKORDERID = bOOKORDERID;
	}

	public List<OrderDetailInfo> getORDERDETAILS()
	{
		return ORDERDETAILS;
	}

	public void setORDERDETAILS(List<OrderDetailInfo> oRDERDETAILS)
	{
		ORDERDETAILS = oRDERDETAILS;
	}

	public Float getAMOUNT()
	{
		return AMOUNT;
	}

	public void setAMOUNT(Float aMOUNT)
	{
		AMOUNT = aMOUNT;
	}

	public Float getPAYAMOUNT()
	{
		return PAYAMOUNT;
	}

	public void setPAYAMOUNT(Float pAYAMOUNT)
	{
		PAYAMOUNT = pAYAMOUNT;
	}

	public List<PaymentInfo> getPAYINFOLIST()
	{
		return PAYINFOLIST;
	}

	public void setPAYINFOLIST(List<PaymentInfo> pAYINFOLIST)
	{
		PAYINFOLIST = pAYINFOLIST;
	}

	public String getCHECKOUTDATE()
	{
		return CHECKOUTDATE;
	}

	public void setCHECKOUTDATE(String cHECKOUTDATE)
	{
		CHECKOUTDATE = cHECKOUTDATE;
	}

	public String getREMARK()
	{
		return REMARK;
	}

	public void setREMARK(String rEMARK)
	{
		REMARK = rEMARK;
	}

	public List<CommentInfo> getCOMMENTS()
	{
		return COMMENTS;
	}

	public void setCOMMENTS(List<CommentInfo> cOMMENTS)
	{
		COMMENTS = cOMMENTS;
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
