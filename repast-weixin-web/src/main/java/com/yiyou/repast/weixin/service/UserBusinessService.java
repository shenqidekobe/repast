package com.yiyou.repast.weixin.service;

import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.merchant.model.UserWhite;
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
	 * 根据ID获取用户
	 * */
	User findById(Long id);
	
	/**
	 * 更新
	 * */
	User update(User obj);
	
	/**
	 * 创建授权记录
	 * */
	UserAuthorizeApply createUserAuthorizeApply(UserAuthorizeApply obj);
	
	/**
	 * 审核授权
	 * */
	void auditUserAuthorizeApply(Long id,boolean flag);
	
	/**
	 * 查询我的授权记录
	 * */
	UserAuthorizeApply getUserAuthorizeApply(Long userId);
	
	/**
	 * id查询
	 * */
	UserAuthorizeApply getUserAuthorizeApplyByID(Long id);
	
	/**
	 * 更新状态
	 * */
	void updateUserAuthorizeApply(UserAuthorizeApply obj);
	
	/**
	 * 发送短信
	 * */
	void sendSms(String phone,String content);
	
	/**
	 * 验证短信
	 * */
	boolean validateSms(String phone,String content);
	
	/**
	 * 用户白名单查询
	 * */
	UserWhite getUserWhite(String phone);
	
	/**
	 * 更新
	 * */
	void updateUserWhite(UserWhite obj);

}
