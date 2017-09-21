package com.yiyou.repast.weixin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.service.CartBusinessService;
import com.yiyou.repast.weixin.service.GoodsBusinessService;
import com.yiyou.repast.weixin.service.UserBusinessService;

/**
 * 商品查询处理
 * */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Resource
	private GoodsBusinessService goodsBusinessService;
	@Resource
	private CartBusinessService cartService;
	@Resource
	private UserBusinessService userService;

	
	@GetMapping("/index")
	public String index(Integer pc,String pt,Model model) {
		model.addAttribute("pc", pc);
		model.addAttribute("pt", pt);
		SessionToken session=userService.getSessionUser();
		Cart cart=cartService.getCart(session.getDeskNum());
		if(cart==null&&StringUtils.isEmpty(session.getDeskNum())) {
			cart=cartService.getCart(session.getUserId());
		}
		model.addAttribute("count",cart==null?0:cart.getItems().size());
		return "goods/list";
	}
	
	@PostMapping("/listData.do")
	public String listData(String type,Model model) {
		Map<String, List<Goods>> map=goodsBusinessService.findGoodsList();
		model.addAttribute("dataMap", map);
		return "goods/list_frag";
	}
	
	@GetMapping("/detail")
	public String detail(Long id,Model model) {
		Goods obj=goodsBusinessService.findGoodsById(id);
		model.addAttribute("obj", obj);
		return "goods/detail";
	}

}
