package com.yiyou.repast.weixin.compent;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.yiyou.repast.merchant.model.Merchant;
import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.weixin.base.Constants;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.service.MerchantBusinessService;
import com.yiyou.repast.weixin.service.UserBusinessService;

@Component
public class MerchantShiroRealm extends AuthorizingRealm{
	
	@Resource
	private UserBusinessService userService;
	@Resource
	private MerchantBusinessService merchantBusinessService;
	
	public MerchantShiroRealm() {
		setName("Wechat MerchantShiroRealm");
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	    return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken user= (UsernamePasswordToken) authcToken;
		String cipher = String.valueOf(user.getPassword());
		String[] arrs=cipher.split("_");
		Long merchantId=Long.valueOf(arrs[0]);
	    String deskNum=arrs[1];
		Long userId=Long.parseLong(user.getUsername());
		User userInfo=userService.findById(userId);
		Merchant merchant=merchantBusinessService.getById(merchantId);
		
		SessionToken session=new SessionToken();
		session.setUserId(userId);
		session.setMerchantId(merchantId);
		session.setPhone(userInfo.getPhone());
		session.setUserName(userInfo.getNickName());
		session.setDeskNum(deskNum);
		session.setOpenId(userInfo.getOpenId());
		session.setToken(UUID.randomUUID().toString());
		session.setCreateTime(new Date());
		session.setIndustry(merchant.getIndustry().name());
		session.setMerchantName(merchant.getName());
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		WebUtils.setSessionAttribute(request, Constants.SESSION_ACCOUNT, session);
		
		return new SimpleAuthenticationInfo(session,userInfo.getOpenId(), getName());
	}


}
