package com.yiyou.repast.merchant.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.base.SessionToken;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.model.MerchantApply;
import com.yiyou.repast.merchant.service.IMerchantAccountService;
import com.yiyou.repast.merchant.service.IMerchantApplyService;

import repast.yiyou.common.util.Md5;

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
	 * 个人中心
	 * */
	@GetMapping("/info")
	public String info(Model model) {
		SessionToken session=(SessionToken) SecurityUtils.getSubject().getPrincipal();
		MerchantAccount obj=this.merchantAccountService.find(session.getAccountId());
		model.addAttribute("obj", obj);
		return "info";
	}
	
	/**
	 * 更新密码
	 * */
	@ResponseBody
	@PostMapping("/setting/save.do")
	public RspResult settingSave(String password,String password1) {
		SessionToken session=(SessionToken) SecurityUtils.getSubject().getPrincipal();
		MerchantAccount obj=this.merchantAccountService.login(session.getMerchantId(), session.getLoginName(), password);
		if(obj==null)return new RspResult(300, "原始密码错误");
		obj.setPassword(Md5.getMD5(password1));
		this.merchantAccountService.update(obj);
		return new RspResult();
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
