package com.sdx.bms.client;

public enum BMSRequestFunc
{
	// 发送验证码
	SendAuthCode(""),
	// 校验验证码
	AuthCode(""),
	// 绑定手机号
	BindUser(""),
	// 绑定车辆
	BindCar(""),
	// 修改用户信息
	UpdateCustomInfo(""),
	// 车型选择列表
	CarCatelog(""),

	// 服务查询
	GetServiceList(""),
	// 可预约时段查询
	GetBookList(""),
	// 服务预约下单
	MakeBookOrder(""),
	// 服务预约订单查询
	getBookOrder(""),

	// 服务订单查询
	getOrderList(""),
	// 服务订单详情
	getOrderDetail(""),
	// 订单支付
	PayOrder(""),

	// 客户套餐余额查询
	GetAccountInfo(""),
	// 客户基本资料查询
	GetCustomerInfo(""),
	// 车辆信息查询接口
	GetCarInfo(""),
	// 车辆信息变更
	UpdateCarInfo(""),
	// 服务评价接口
	MakeComment(""),
	;

	private String uri;

	private BMSRequestFunc(String uri)
	{
		this.uri = uri;
	}

	public String uri()
	{
		return this.uri;
	}
}
