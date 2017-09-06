package com.yiyou.repast.merchant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商户管理员控制器
 * */
@Controller
@RequestMapping("/account")
public class AccountController {

	@GetMapping()
	public String list(Model model) {
		return "/account/list";
	}

}
