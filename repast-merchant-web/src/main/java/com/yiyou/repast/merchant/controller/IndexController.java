package com.yiyou.repast.merchant.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.MerchantApply;
import com.yiyou.repast.merchant.service.IMerchantAccountService;
import com.yiyou.repast.merchant.service.IMerchantApplyService;

/**
 * 商户平台首页管理中心
 * */
@Controller
public class IndexController {
	
	@Reference
	private IMerchantAccountService merchantAccountService;
	@Reference
	private IMerchantApplyService merchantApplyService;
	

	/**
	 * 商户登陆
	 * */
	@GetMapping("/{applyPath}/login")
	public String merchantLogin(@PathVariable String applyPath,HttpServletRequest request) {
		MerchantApply apply=merchantApplyService.findMerchantApplyByPath(applyPath);
		if(apply==null)return "unauth";//未注册商户
		request.getSession().setAttribute(Constants.SESSION_MERCHANTID_KEY, apply.getMerchantId());
		return "login";
	}
	
	/**
	 * 系统首页
	 * */
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
