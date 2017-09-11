package com.yiyou.repast.weixin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiyou.repast.weixin.service.CartBusinessService;

/**
 * 我的购物车处理中心
 * */
@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Resource
	private CartBusinessService cartService;
	
	
	@GetMapping("")
	public String index() {
		return "";
	}

}
