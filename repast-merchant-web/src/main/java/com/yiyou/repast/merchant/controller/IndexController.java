package com.yiyou.repast.merchant.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.service.IMerchantAccountService;

/**
 * 商户平台首页管理中心
 * */
@Controller
public class IndexController {
	
	@Reference
	private IMerchantAccountService merchantAccountService;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 管理登录
	 * */
	@ResponseBody
	@PostMapping("/ajaxLogin.do")
	public RspResult ajaxLogin(String loginName,String password) {
	    try {
	    	UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
	        SecurityUtils.getSubject().login(token);
	        return new RspResult();
	    } catch (Exception e) {
	    	return new RspResult(500,e.getMessage());
	    }
	}
	
	/**
	 * 登出
	 * */
	@GetMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "login";
	}


}
