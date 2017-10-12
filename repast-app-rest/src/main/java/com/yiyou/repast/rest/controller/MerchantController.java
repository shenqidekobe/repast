package com.yiyou.repast.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiyou.repast.merchant.model.Merchant;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.service.IMerchantAccountService;
import com.yiyou.repast.merchant.service.IMerchantService;
import com.yiyou.repast.rest.base.AppResult;
import com.yiyou.repast.rest.base.OnlineAccount;
import com.yiyou.repast.rest.base.SessionToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import repast.yiyou.common.base.EnumDefinition.AccountType;
import repast.yiyou.common.util.TokenProcessor;

@Api(value = "商户接口", tags = "商户模块")
@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
	
	private static ObjectMapper objectMapper=new ObjectMapper();
	
	@Reference
	private IMerchantAccountService merchantAccountService;
	@Reference
	private IMerchantService merchantService;

	/**
	 * 终端用户登录
	 * */
	@PostMapping("/login")
	@ApiOperation(value = "终端用户登录", notes = "商家终端用户登录对订单进行处理")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "account", value = "帐号", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
		@ApiImplicitParam(name = "merchantId", value = "商户ID", required = true, dataType = "Long")})
	public AppResult list(String account,String password,Long merchantId) throws Exception{
		Merchant merchant=merchantService.find(merchantId);
		MerchantAccount obj=merchantAccountService.login(merchantId, account, password);
		//验证登录用户的帐号密码是否正确
		if(obj==null)return new AppResult(AppResult.OBJECT_NULL,"帐号密码错误");
		//验证登录用户是否是终端的接单用户
		if(!AccountType.terminal.equals(obj.getType()))return new AppResult(AppResult.VALID_FIAL,"此帐号不允许登录");
		//注册到在线用户池
		OnlineAccount.register(obj.getId(), merchantId, account);
		
		SessionToken session=new SessionToken();
		session.setAccountId(obj.getId());
		session.setAccount(account);
		session.setMerchantId(obj.getMerchantId());
		session.setMerchantName(merchant.getName());
		session.setIndustry(merchant.getIndustry().name());
		
		Map<String , Object> map = new HashMap<String, Object>(){
			private static final long serialVersionUID = 7206738988337455302L;
		{  
			put("accountId", obj.getId());  
			put("merchantId", obj.getMerchantId());  
			put(account,account);  
		}};  
		String token=TokenProcessor.produceToken(map);
		
		session.setToken(token);
		session.setLoginTime(new Date());
		return new AppResult(objectMapper.writeValueAsString(session));
	}
	
}
