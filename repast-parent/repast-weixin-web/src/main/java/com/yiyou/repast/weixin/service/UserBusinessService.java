package com.yiyou.repast.weixin.service;

import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.merchant.model.UserAuthorizeApply;
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
	
	/**
	 * 创建授权记录
	 * */
	void createUserAuthorizeApply(UserAuthorizeApply obj);
	
	/**
	 * 审核授权
	 * */
	void auditUserAuthorizeApply(Long id,boolean flag);
	
	/**
	 * 查询我的授权记录
	 * */
	UserAuthorizeApply getUserAuthorizeApply(Long userId);

}
