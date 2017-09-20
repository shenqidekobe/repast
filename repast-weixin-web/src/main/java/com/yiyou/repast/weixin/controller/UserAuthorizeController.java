package com.yiyou.repast.weixin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.weixin.base.RspResult;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.service.UserBusinessService;

import repast.yiyou.common.base.EnumDefinition.AuthorizeAuditStaus;

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
		RspResult rsp=new RspResult();
		SessionToken session=userService.getSessionUser();
		obj.setUserId(session.getUserId());
		obj.setUserName(session.getUserName());
		obj.setMerchantId(session.getMerchantId());
		obj=userService.createUserAuthorizeApply(obj);
		rsp.setData(obj.getId());
		return rsp;
	}
	

	/**
	 * 领导进入审批
	 * */
	@GetMapping("/audit")
	public String audit(Model model,Long id) {
		UserAuthorizeApply obj=userService.getUserAuthorizeApplyByID(id);
		model.addAttribute("obj", obj);
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
	public String process(Long id) {
		String current="handling";
		SessionToken session=this.userService.getSessionUser();
		UserAuthorizeApply obj=userService.getUserAuthorizeApply(session.getUserId());
		if(AuthorizeAuditStaus.pass.equals(obj.getAuditStatus())) {
			current="success";
		}else if(AuthorizeAuditStaus.refuse.equals(obj.getAuditStatus())) {
			current="fail";
		}
		return "/authorize/"+current;
	}

}
