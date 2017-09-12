package com.yiyou.repast.weixin.service;

import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.weixin.base.SessionToken;

public interface UserBusinessService {

	/**
	 * 注册新用户保存
	 * */
	User registerUser(User user);
	
	/**
	 * 获取当前session信息
	 * */
	SessionToken getSessionUser();

}
