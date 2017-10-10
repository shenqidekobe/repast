package com.yiyou.repast.merchant.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

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
	
	private static final String APPLY_PATH_COOKIE="COOKIE_APPLYPATH";

	/**
	 * 商户登陆
	 * */
	@GetMapping("/{applyPath}/login")
	public String merchantLogin(@PathVariable String applyPath,HttpServletRequest request) {
		MerchantApply apply=merchantApplyService.findMerchantApplyByPath(applyPath);
		if(apply==null)return "unauth";//未注册商户
		request.getSession().setAttribute(Constants.SESSION_MERCHANTID_KEY, apply.getMerchantId());
		request.getSession().setAttribute(Constants.SESSION_MERCHANTAPPLY_KEY, applyPath);
		return "loginN";//新版的登录页面，旧版本的登录页为login
	}
	
	/**
	 * 系统首页
	 * */
	@GetMapping("/index")
	public String index(HttpServletRequest request) {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		Cookie cookie=WebUtils.getCookie(request, APPLY_PATH_COOKIE);
		if(cookie==null||cookie.getValue()==null)return "404";
		String path=cookie.getValue();
		return "redirect:/"+path+"/login";
	}

	/**
	 * 管理登录
	 * */
	@ResponseBody
	@PostMapping("/ajaxLogin.do")
	public RspResult ajaxLogin(String loginName,String password,HttpServletRequest req,HttpServletResponse rsp) {
	    try {
	    	UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
	        SecurityUtils.getSubject().login(token);
	        //登录成功，保存当前商户的应用域名
	        String path=(String) req.getSession().getAttribute(APPLY_PATH_COOKIE);
	        Cookie cookie = new Cookie(APPLY_PATH_COOKIE,path);
		    cookie.setPath("/");
		    rsp.addCookie(cookie);
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
