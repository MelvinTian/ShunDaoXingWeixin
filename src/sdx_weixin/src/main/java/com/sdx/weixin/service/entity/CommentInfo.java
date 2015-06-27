package com.sdx.weixin.service.entity;


public class CommentInfo
{
	private String COMMENTID;// 评价ID
	private String CUSTOMERID;// 客户ID
	private String OUSERID;// 第三方用户ID
	private String COMMENTDESC;// 评价内容
	private int RANK;// 总体评级
	private int SERVICERANK;// 服务评级
	private int QUALRANK;// 质量评级
	private String CREATEDATE;// 时间

	public String getCOMMENTID()
	{
		return COMMENTID;
	}

	public void setCOMMENTID(String cOMMENTID)
	{
		COMMENTID = cOMMENTID;
	}

	public String getCUSTOMERID()
	{
		return CUSTOMERID;
	}

	public void setCUSTOMERID(String cUSTOMERID)
	{
		CUSTOMERID = cUSTOMERID;
	}

	public String getOUSERID()
	{
		return OUSERID;
	}

	public void setOUSERID(String oUSERID)
	{
		OUSERID = oUSERID;
	}

	public String getCOMMENTDESC()
	{
		return COMMENTDESC;
	}

	public void setCOMMENTDESC(String cOMMENTDESC)
	{
		COMMENTDESC = cOMMENTDESC;
	}

	public int getRANK()
	{
		return RANK;
	}

	public void setRANK(int rANK)
	{
		RANK = rANK;
	}

	public int getSERVICERANK()
	{
		return SERVICERANK;
	}

	public void setSERVICERANK(int sERVICERANK)
	{
		SERVICERANK = sERVICERANK;
	}

	public int getQUALRANK()
	{
		return QUALRANK;
	}

	public void setQUALRANK(int qUALRANK)
	{
		QUALRANK = qUALRANK;
	}

	public String getCREATEDATE()
	{
		return CREATEDATE;
	}

	public void setCREATEDATE(String cREATEDATE)
	{
		CREATEDATE = cREATEDATE;
	}
}
