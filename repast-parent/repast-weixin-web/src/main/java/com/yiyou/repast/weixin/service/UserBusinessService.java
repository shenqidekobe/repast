package com.yiyou.repast.weixin.service;

import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.merchant.model.UserWhite;
import com.yiyou.repast.weixin.base.SessionToken;

import repast.yiyou.common.exception.BusinessException;

public interface UserBusinessService {

	/**
	 * 注册新用户保存
	 * */
	User registerUser(User user)throws BusinessException;
	
	/**
	 * 获取当前session信息
	 * */
	SessionToken getSessionUser()throws BusinessException;
	
	/**
	 * 根据ID获取用户
	 * */
	User findById(Long id)throws BusinessException;
	
	/**
	 * 更新
	 * */
	User update(User obj)throws BusinessException;
	
	/**
	 * 创建授权记录
	 * */
	UserAuthorizeApply createUserAuthorizeApply(UserAuthorizeApply obj)throws BusinessException;
	
	/**
	 * 审核授权
	 * */
	void auditUserAuthorizeApply(Long userId,Long id,boolean flag)throws BusinessException;
	
	/**
	 * 查询我的授权记录
	 * */
	UserAuthorizeApply getUserAuthorizeApply(Long userId)throws BusinessException;
	
	/**
	 * id查询
	 * */
	UserAuthorizeApply getUserAuthorizeApplyByID(Long id)throws BusinessException;
	
	/**
	 * 更新状态
	 * */
	void updateUserAuthorizeApply(UserAuthorizeApply obj)throws BusinessException;
	
	/**
	 * 发送短信
	 * */
	void sendSms(String phone,String content)throws BusinessException;
	
	/**
	 * 验证短信
	 * */
	boolean validateSms(String phone,String content)throws BusinessException;
	
	/**
	 * 用户白名单查询
	 * */
	UserWhite getUserWhite(String phone)throws BusinessException;
	
	/**
	 * 更新
	 * */
	void updateUserWhite(UserWhite obj)throws BusinessException;

}
