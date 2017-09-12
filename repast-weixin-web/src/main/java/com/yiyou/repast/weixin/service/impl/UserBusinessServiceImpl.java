package com.yiyou.repast.weixin.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.merchant.service.IUserService;
import com.yiyou.repast.weixin.base.RBeanUtils;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.service.UserBusinessService;

import repast.yiyou.common.exception.BusinessException;

@Service
public class UserBusinessServiceImpl implements UserBusinessService{
	
	@Reference
	private IUserService userService;

	@Override
	public User registerUser(User user) {
		if(user==null) {
			throw new BusinessException(4444, "user not be null");
		}
		if(StringUtils.isEmpty(user.getOpenId())) {
			throw new BusinessException(4444, "user openId not be null");
		}
		String openId=user.getOpenId();
		User obj=userService.findByOpenId(openId);
		if(obj==null) {
			userService.save(user);
		}else {
			RBeanUtils.copyProperties(user, obj);
			userService.update(obj);
		}
		return user;
	}

	@Override
	public SessionToken getSessionUser() {
		if(SecurityUtils.getSubject()==null)return null;
		
		return (SessionToken) SecurityUtils.getSubject().getPrincipal();
	}

}
