package com.yiyou.repast.merchant.compent;

import java.util.Date;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.SessionToken;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.model.MerchantMenu;
import com.yiyou.repast.merchant.service.IMerchantAccountService;

import repast.yiyou.common.base.EnumDefinition.AccountStaus;

public class MerchantShiroRealm extends AuthorizingRealm{
	
	@Reference
	private IMerchantAccountService merchantAccountService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	    SessionToken session  = (SessionToken)principal.getPrimaryPrincipal();
	    MerchantAccount account=this.merchantAccountService.find(session.getAccountId());
	    authorizationInfo.addRole(account.getRole().getName());
	    for(MerchantMenu menu:account.getRole().getMenus()){
	        authorizationInfo.addStringPermission(menu.getPermission());
	    }
	    return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken user= (UsernamePasswordToken) authcToken;
		String password = String.valueOf(user.getPassword());  
		MerchantAccount account=merchantAccountService.login(user.getUsername(),password);
		if (null == account) {
	        throw new AccountException("帐号或密码不正确！");
	    }else if(account.getStatus().equals(AccountStaus.disable)){
	        throw new DisabledAccountException("帐号已经禁止登录！");
	    }else{
	    	account.setLoginTime(new Date());
	    	merchantAccountService.update(account);
	    }
		SessionToken session=new SessionToken();
		session.setAccountId(account.getId());
		session.setLoginName(user.getUsername());
		session.setRoleId(account.getRole().getId());
		session.setRoleName(account.getRole().getName());
		session.setCreateTime(new Date());
		return new SimpleAuthenticationInfo(session,account.getPassword(), getName());
	}


}
