package com.yiyou.repast.weixin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.weixin.base.RspResult;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.service.CartBusinessService;
import com.yiyou.repast.weixin.service.OrderBusinessService;
import com.yiyou.repast.weixin.service.UserBusinessService;

/**
 * 我的订单处理中心
 * */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private UserBusinessService userService;
	@Resource
	private OrderBusinessService orderService;
	@Resource
	private CartBusinessService cartService;
	
	/**
	 * 我的当前订单
	 * */
	@GetMapping("")
	public String index(Model model) {
		SessionToken session=userService.getSessionUser();
		Order order=orderService.getOrder(session.getUserId());
		model.addAttribute("obj", order);
		return "";
	}
	
	/**
	 * 生成订单
	 * */
	@PostMapping("/produce.do")
	@ResponseBody
	public RspResult produceOrder() {
		RspResult rsp=new RspResult();
		SessionToken session=userService.getSessionUser();
		Cart cart=cartService.getCart(session.getUserId());
		Order order=orderService.createOrder(cart);
		rsp.setData(order);
		return rsp;
	}

}
