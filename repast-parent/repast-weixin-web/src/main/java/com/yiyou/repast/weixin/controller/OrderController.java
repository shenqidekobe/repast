package com.yiyou.repast.weixin.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.merchant.model.UserWhite;
import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;
import com.yiyou.repast.weixin.base.RspResult;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.compent.WechatProperties;
import com.yiyou.repast.weixin.service.CartBusinessService;
import com.yiyou.repast.weixin.service.OrderBusinessService;
import com.yiyou.repast.weixin.service.UserBusinessService;

import repast.yiyou.common.base.EnumDefinition.AuthorizeAuditStaus;
import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.base.EnumDefinition.PayWay;

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
	@Resource
	private WechatProperties wechatProperties;
	
	/**
	 * 我的当前订单
	 * */
	@GetMapping("")
	public String index(Model model) {
		SessionToken session=userService.getSessionUser();
		Order order=orderService.getOrder(session.getUserId());
		if(order==null)return "redirect:/cart/list";
		Map<String,Integer> typeMap=new HashMap<>();//分类map
		for(OrderItem item:order.getItems()) {
			if(typeMap.containsKey(item.getGoodsType())) {
				typeMap.put(item.getGoodsType(), typeMap.get(item.getGoodsType())+1);
			}else {
				typeMap.put(item.getGoodsType(), 1);
			}
		}
		model.addAttribute("count", order.getItems().size());
		model.addAttribute("typeMap",typeMap);
		model.addAttribute("obj", order);
		model.addAttribute("imgDomain", wechatProperties.getImgDomain());
		return "goods/success";
	}
	
	/**
	 * 生成订单
	 * */
	@ResponseBody
	@PostMapping("/produce.do")
	public RspResult produceOrder(Long cid,String realName,String phone,String address,HttpServletRequest req) {
		Cart cart=cartService.getCartById(cid);
		if(cart==null) {
			return new RspResult(402,"请勿重复下单");
		}else if(CollectionUtils.isEmpty(cart.getItems())){
			return new RspResult(402,"购物车没有任何商品不能下单");
		}
		Order order=orderService.createOrder(cart,realName,phone,address);
		RspResult rsp=new RspResult();
		rsp.setData(order.getId());
		//清空购物车
		cartService.clearCart(cid);
		return rsp;
	}
	
	/**
	 * 开始结算页面
	 * */
	@GetMapping("/settle")
	public String settle(Model model,Long id) {
		Order order=orderService.getOrderById(id);
		if(order==null)return "redirect:/cart/list";
		model.addAttribute("count", order.getItems().size());
		model.addAttribute("obj", order);
		return "goods/settle";
	}
	
	/**
	 * 订单结算
	 * */
	@PostMapping("/settle/save.do")
	@ResponseBody
	public RspResult settleOrder(Long cid) {
		RspResult rsp=new RspResult();
		Order order=this.orderService.getOrderById(cid);
		SessionToken session=userService.getSessionUser();
		String phone=session.getPhone();
		UserWhite white=userService.getUserWhite(phone);
		if(white==null) {
			UserAuthorizeApply apply=userService.getUserAuthorizeApply(session.getUserId());
			if(apply==null) {
				return new RspResult(400,"无权限结算订单，请联系管理员");
			}
			apply.setAuditStatus(AuthorizeAuditStaus.use);
			this.userService.updateUserAuthorizeApply(apply);
			order.setStatus(OrderStaus.settle);
			order.setPayWay(PayWay.authorize);
		}else {
			switch (white.getType()) {
			case common:
				BigDecimal charge=white.getChargeAamount();
				if(charge.compareTo(order.getAmount())==-1) {
					return new RspResult(444,"卡内余额不足，请联系管理员充值");
				}
				white.setChargeAamount(charge.subtract(order.getAmount()));
				this.userService.updateUserWhite(white);
				
				order.setStatus(OrderStaus.settle);
				order.setPayWay(PayWay.card);
				break;
			case senior:
				order.setStatus(OrderStaus.settle);
				order.setPayWay(PayWay.senior);
				break;
			default:
				return new RspResult(443,"不是本店会员，不能结算");
			}
		}
		this.orderService.settleOrder(order);
		return rsp;
	}

}
