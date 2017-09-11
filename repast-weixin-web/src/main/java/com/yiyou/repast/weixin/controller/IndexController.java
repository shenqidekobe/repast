package com.yiyou.repast.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 进入首页管理
 * */
@Controller
public class IndexController {
	
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	/**
	 * 绑定手机号
	 * */
	@ResponseBody
	@PostMapping("/bind/phone")
	public String bindPhone(String phone,String code) {
		return null;
	}

}
