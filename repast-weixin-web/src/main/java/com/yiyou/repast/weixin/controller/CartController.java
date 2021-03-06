package com.yiyou.repast.weixin.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.weixin.base.CartItemMap;
import com.yiyou.repast.weixin.base.RspResult;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.compent.WechatProperties;
import com.yiyou.repast.weixin.service.CartBusinessService;
import com.yiyou.repast.weixin.service.GoodsBusinessService;
import com.yiyou.repast.weixin.service.UserBusinessService;

import repast.yiyou.common.base.EnumDefinition.IndustryType;

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
	@Resource
	private GoodsBusinessService goodsBusinessService;
	@Resource
	private WechatProperties wechatProperties;
	
	/**
	 * 选择人数，验证行业类型，非点餐的直接跳过该步骤
	 * */
	@GetMapping("/people")
	public String people() {
		SessionToken session=userService.getSessionUser();
		if(IndustryType.catering.name().equals(session.getIndustry())) {
			return "people";
		}
		return "redirect:/goods/index";
	}
	
	/**
	 * 我的购物车
	 * 按用户点的分类处理
	 * */
	@GetMapping("/list")
	public String myCart(Model model) {
		SessionToken session=userService.getSessionUser();
		Cart cart=cartService.getCart(session.getDeskNum());
		if(cart==null&&StringUtils.isEmpty(session.getDeskNum())) {
			cart=cartService.getCart(session.getUserId());
		}
		if(cart==null) {
			List<Goods> map=goodsBusinessService.findHotGoodsList(9L).values().stream()
					.flatMap(gr->gr.stream()).collect(Collectors.toList());//热销榜，9条
			model.addAttribute("hotList", map);
			return "goods/cart_empty";
		}
		CartItemMap cmap=cartService.cartToMap(cart);
		model.addAttribute("cart", cart);
		model.addAttribute("count", cart.getItems().size());
		model.addAttribute("itemList",cmap.getList());
		model.addAttribute("typeMap",cmap.getTypeMap());
		return "goods/cart_list";
	}
	
	/**
	 * 商品加入购物车
	 * */
	@ResponseBody
	@PostMapping("/save.do")
	public RspResult saveCart(Long id,String auxIds,Integer number,String amount,
			String goodsType,String goodsName,Long specId,String specName,
			HttpServletRequest request) {
		SessionToken session=userService.getSessionUser();
		Integer peopleCount=(Integer) request.getSession().getAttribute("peopleCount");
		String predictDate=(String) request.getSession().getAttribute("predictDate");
		cartService.addCart(session.getUserId(),session.getUserName(),session.getDeskNum(),
				id, auxIds, number,peopleCount,predictDate,goodsName,specId,specName);
		return new RspResult();
	}
	
	/**
	 * 清空购物车
	 * */
	@ResponseBody
	@PostMapping("/clear.do")
	public RspResult clearCart(Long id) {
		cartService.clearCart(id);
		return new RspResult();
	}
	

}
