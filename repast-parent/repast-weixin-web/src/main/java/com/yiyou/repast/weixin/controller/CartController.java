package com.yiyou.repast.weixin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiyou.repast.weixin.service.CartBusinessService;
import com.yiyou.repast.weixin.service.UserBusinessService;

/**
 * 我的购物车处理中心
 * */
@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Resource
	private UserBusinessService userService;
	@Resource
	private CartBusinessService cartService;
	
	@GetMapping("/people")
	public String people() {
		return "people";
	}
	
	/**
	 * 我的购物车
	 * */
	@GetMapping("/list")
	public String myCart() {
		return "";
	}
	
	/**
	 * 商品加入购物车
	 * */
	@PostMapping("/save.do")
	public String saveCart(Long goodsId) {
		return "";
	}

}
