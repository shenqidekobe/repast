package com.yiyou.repast.merchant.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.service.IMerchantAccountService;

/**
 * 商户管理员控制器
 * */
@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Reference
	private IMerchantAccountService merchantAccountService;

	@GetMapping()
	public String list(Model model) {
		return "/account/list";
	}
	
	@ResponseBody
	@PostMapping("/listData.do")
	public List<MerchantAccount> listData(Model model) {
		return merchantAccountService.findAll();
	}

}
