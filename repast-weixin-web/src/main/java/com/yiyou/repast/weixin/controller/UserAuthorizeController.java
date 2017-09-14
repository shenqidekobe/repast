package com.yiyou.repast.weixin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.weixin.base.RspResult;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.service.UserBusinessService;

/**
 * 用户申请授权，部门领导签字才可开始点菜
 * */
@Controller
@RequestMapping("/authorize")
public class UserAuthorizeController {
	
	@Resource
	private UserBusinessService userService;
	
	/**
	 * 开始申请授权
	 * */
	@GetMapping("")
	public String apply() {
		return "/authorize/apply";
	}
	
	@ResponseBody
	@PostMapping("/apply/save.do")
	public RspResult applySave(UserAuthorizeApply obj) {
		SessionToken session=userService.getSessionUser();
		obj.setUserId(session.getUserId());
		obj.setMerchantId(session.getMerchantId());
		userService.createUserAuthorizeApply(obj);
		return new RspResult();
	}
	

	/**
	 * 领导进入审批
	 * */
	@GetMapping("/audit")
	public String audit() {
		return "/authorize/audit";
	}
	
	@ResponseBody
	@PostMapping("/audit/save.do")
	public RspResult auditSave(Long id,String status) {
		boolean flag="pass".equals(status);
		userService.auditUserAuthorizeApply(id, flag);
		return new RspResult();
	}
	
	/**
	 * 当前处理进度
	 * */
	@GetMapping("/process")
	public String process() {
		String current="";
		return "/authorize/"+current;
	}

}
