package com.yiyou.repast.weixin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.weixin.base.RspResult;
import com.yiyou.repast.weixin.service.UserBusinessService;

/**
 * 进入首页管理
 * */
@Controller
public class IndexController {
	
	@Resource
	private UserBusinessService userService;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
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
		User obj=userService.findById(userService.getSessionUser().getUserId());
		obj.setPhone(phone);
		userService.update(obj);
		return new RspResult();
	}

}
