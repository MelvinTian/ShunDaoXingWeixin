package com.sdx.weixin.service.entity;

import java.util.List;

public class ServiceBookInfo
{
	private String SERVICEBOOKID;// 序号
	private String BOOKDATE;// 预约日期
	private String SERVICEID;// 服务项目
	private String SERVICETYPE;// 服务方式
	private List<BookInfo> BOOKLIST;// 预约信息

	public String getSERVICEBOOKID()
	{
		return SERVICEBOOKID;
	}

	public void setSERVICEBOOKID(String sERVICEBOOKID)
	{
		SERVICEBOOKID = sERVICEBOOKID;
	}

	public String getBOOKDATE()
	{
		return BOOKDATE;
	}

	public void setBOOKDATE(String bOOKDATE)
	{
		BOOKDATE = bOOKDATE;
	}

	public String getSERVICEID()
	{
		return SERVICEID;
	}

	public void setSERVICEID(String sERVICEID)
	{
		SERVICEID = sERVICEID;
	}

	public String getSERVICETYPE()
	{
		return SERVICETYPE;
	}

	public void setSERVICETYPE(String sERVICETYPE)
	{
		SERVICETYPE = sERVICETYPE;
	}

	public List<BookInfo> getBOOKLIST()
	{
		return BOOKLIST;
	}

	public void setBOOKLIST(List<BookInfo> bOOKLIST)
	{
		BOOKLIST = bOOKLIST;
	}
}
