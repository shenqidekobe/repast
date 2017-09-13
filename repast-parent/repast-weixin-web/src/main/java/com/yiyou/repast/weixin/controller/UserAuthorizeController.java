package com.yiyou.repast.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户申请授权，部门领导签字才可开始点菜
 * */
@Controller
@RequestMapping("/authorize")
public class UserAuthorizeController {
	
	
	/**
	 * 开始申请授权
	 * */
	@GetMapping("")
	public String apply() {
		return "/authorize/apply";
	}
	

	/**
	 * 领导进入审批
	 * */
	@GetMapping("/audit")
	public String audit() {
		return "/authorize/audit";
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
