package com.yiyou.repast.weixin.controller;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.merchant.model.UserWhite;
import com.yiyou.repast.weixin.base.RspResult;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.service.UserBusinessService;

import repast.yiyou.common.base.EnumDefinition.AuthorizeAuditStaus;

/**
 * 进入首页管理
 * */
@Controller
public class IndexController {
	
	@Resource
	private UserBusinessService userService;
	
	/**
	 * 系统首页
	 * 1、验证是否绑定手机号码
	 * 2、验证用户是否白名单，如是申请授权则验证授权是否审核通过
	 * */
	@GetMapping("/index")
	public String index(Model model) {
		SessionToken session=userService.getSessionUser();
		String phone=session.getPhone();
		if(StringUtils.isEmpty(phone)) {
			return "redirect:/bind/phone";//去绑定手机
		}
		UserWhite white=userService.getUserWhite(phone);
		if(white==null) {
			UserAuthorizeApply apply=userService.getUserAuthorizeApply(session.getUserId());
			long nd=1000 * 60 * 60;//验证是否是当天24小时内
			if(apply==null||AuthorizeAuditStaus.use.equals(apply.getAuditStatus())
					||(new Date().getTime()-apply.getCreateTime().getTime())%nd/nd>24) {
				return "redirect:/access";//无权限
			}else if(AuthorizeAuditStaus.await.equals(apply.getAuditStatus())
					||AuthorizeAuditStaus.refuse.equals(apply.getAuditStatus())) {
				return "redirect:/authorize/process?id="+apply.getId();
			}
		}
		return "index";
	}
	
	/**
	 * 绑定手机号码
	 * */
	@GetMapping("/bind/phone")
	public String bind() {
		return "bind";
	}
	
	/**
	 * 绑定手机号
	 * */
	@ResponseBody
	@PostMapping("/bind/phone/save.do")
	public RspResult bindPhone(String phone,String code) {
		if(!userService.validateSms(phone, code)) {
			return new RspResult(300,"手机验证码错误");
		}
		User obj=userService.findById(userService.getSessionUser().getUserId());
		obj.setPhone(phone);
		userService.update(obj);
		return new RspResult();
	}
	
	/**
	 * 发送手机验证码
	 * */
	@ResponseBody
	@PostMapping("/sms/send.do")
	public RspResult sendCode(String phone) {
		try {
			Random random = new Random();
			String content = "";
			for (int i = 0; i < 6; i++) {
				content += random.nextInt(10);
			}
			String text=content+",请勿告诉他人。";
			this.userService.sendSms(phone, "【法家云】验证码：" +text );
		} catch (Exception e) {
		}
		return new RspResult();
	}
	
	
	@GetMapping("/access")
	public String access() {
		return "access";
	}
	@GetMapping("/error")
	public String error() {
		return "error";
	}

}
