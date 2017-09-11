package com.yiyou.repast.weixin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiyou.repast.weixin.service.OrderBusinessService;

/**
 * 我的订单处理中心
 * */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private OrderBusinessService orderService;
	
	@GetMapping("")
	public String index() {
		return "";
	}

}
