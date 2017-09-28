package com.yiyou.repast.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.service.IMerchantAccountService;
import com.yiyou.repast.rest.base.AppResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import repast.yiyou.common.base.EnumDefinition.AccountType;

@Api(value = "商户接口", tags = "商户模块")
@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
	
	@Reference
	private IMerchantAccountService merchantAccountService;

	/**
	 * 终端用户登录
	 * */
	@PostMapping("/login")
	@ApiOperation(value = "终端用户登录", notes = "商家终端用户登录对订单进行处理")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "account", value = "帐号", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
		@ApiImplicitParam(name = "merchantId", value = "商户ID", required = true, dataType = "Long")})
	public AppResult list(String account,String password,Long merchantId) {
		MerchantAccount obj=merchantAccountService.login(merchantId, account, password);
		
		if(obj==null)return new AppResult(AppResult.OBJECT_NULL,"帐号密码错误");
		
		if(!AccountType.terminal.equals(obj.getType()))return new AppResult(AppResult.VALID_FIAL,"此帐号不允许登录");
		
		return new AppResult(new Gson().toJson(obj));
	}

}
