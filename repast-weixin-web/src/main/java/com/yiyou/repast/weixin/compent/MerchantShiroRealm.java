package com.yiyou.repast.weixin.compent;

import java.util.Date;
import java.util.UUID;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yiyou.repast.weixin.base.SessionToken;

public class MerchantShiroRealm extends AuthorizingRealm{
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	    return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken user= (UsernamePasswordToken) authcToken;
		String openId = String.valueOf(user.getPassword());  
		SessionToken session=new SessionToken();
		session.setUserId(Long.parseLong(user.getUsername()));
		session.setOpenId(openId);
		session.setToken(UUID.randomUUID().toString());
		session.setCreateTime(new Date());
		return new SimpleAuthenticationInfo(session,openId, getName());
	}


}
