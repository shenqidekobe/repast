package com.yiyou.repast.weixin.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.merchant.service.IUserAuthorizeApplyService;
import com.yiyou.repast.merchant.service.IUserService;
import com.yiyou.repast.weixin.base.RBeanUtils;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.service.UserBusinessService;

import repast.yiyou.common.base.EnumDefinition.AuthorizeAuditStaus;
import repast.yiyou.common.exception.BusinessException;

@Service
public class UserBusinessServiceImpl implements UserBusinessService{
	
	@Reference
	private IUserService userService;
	@Reference
	private IUserAuthorizeApplyService userAuthorizeApplyService;

	@Override
	public User registerUser(User user) {
		if(user==null) {
			throw new BusinessException(4444, "user must not be null");
		}
		if(StringUtils.isEmpty(user.getOpenId())) {
			throw new BusinessException(4444, "user openId must not be null");
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
		
		SessionToken session=(SessionToken) SecurityUtils.getSubject().getPrincipal();
		session=new SessionToken();
		session.setUserId(100l);
		return session;
	}
	
	@Override
	public User findById(Long id) {
		User obj=userService.findById(null, id);
		if(obj==null) {
			throw new BusinessException(4444, "object userAuthorizeApply must not be null");
		}
		return obj;
	}
	
	@Override
	public User update(User obj) {
		if(obj==null||obj.getId()==null) {
			throw new BusinessException(4444, "object userAuthorizeApply must not be null");
		}
		return userService.update(obj);
	}

	@Override
	public void createUserAuthorizeApply(UserAuthorizeApply obj) {
		if(obj==null) {
			throw new BusinessException(4444, "object userAuthorizeApply must not be null");
		}
		obj.setAuditStatus(AuthorizeAuditStaus.await);
		obj.setCreateTime(new Date());
		userAuthorizeApplyService.save(obj);
	}

	@Override
	public void auditUserAuthorizeApply(Long id, boolean flag) {
		if(id==null) {
			throw new BusinessException(4444, "id must not be null");
		}
		UserAuthorizeApply obj=this.userAuthorizeApplyService.findById(id);
		if(obj==null) {
			throw new BusinessException(4444, "object userAuthorizeApply must not be null");
		}
		AuthorizeAuditStaus status=flag?AuthorizeAuditStaus.pass:AuthorizeAuditStaus.refuse;
		obj.setAuditStatus(status);
		obj.setAuditTime(new Date());
		userAuthorizeApplyService.update(obj);
	}

	@Override
	public UserAuthorizeApply getUserAuthorizeApply(Long userId) {
		if(userId==null) {
			throw new BusinessException(4444, "userId must not be null");
		}
		return userAuthorizeApplyService.findByUserId(userId);
	}

}
