package com.yiyou.repast.weixin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.weixin.base.RspResult;
import com.yiyou.repast.weixin.base.SessionToken;
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
	public String myCart(Model model) {
		SessionToken session=userService.getSessionUser();
		Cart cart=cartService.getCart(session.getUserId());
		if(cart==null) {
			return "goods/cart_empty";
		}
		model.addAttribute("cart", cart);
		return "goods/cart_list";
	}
	
	/**
	 * 商品加入购物车
	 * */
	@ResponseBody
	@PostMapping("/save.do")
	public RspResult saveCart(Long id,String auxIds,Integer number,String amount,HttpServletRequest request) {
		String deskNum=(String) request.getSession().getAttribute("deskNum");
		SessionToken session=userService.getSessionUser();
		cartService.addCart(session.getUserId(), deskNum, id, auxIds, number, amount);
		return new RspResult();
	}

}
