package com.sdx.weixin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdx.common.entity.UserBind;
import com.sdx.common.mapper.UserBindMapper;
import com.sdx.weixin.service.IUserService;

@Service
public class UserServiceImpl implements IUserService
{
	@Autowired
	private UserBindMapper userMapper;
	
	@Override
	public void bindUser(String userId, String weixinId)
	{
		UserBind user = new UserBind();
		user.setUid(userId);
		user.setWeixinId(weixinId);
		userMapper.insert(user);
	}
}