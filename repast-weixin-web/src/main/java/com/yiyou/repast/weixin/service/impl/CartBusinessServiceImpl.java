package com.yiyou.repast.weixin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.CartItem;
import com.yiyou.repast.order.service.ICartService;
import com.yiyou.repast.order.service.IOrderService;
import com.yiyou.repast.weixin.base.CartItemMap;
import com.yiyou.repast.weixin.base.ThreadContextHolder;
import com.yiyou.repast.weixin.service.CartBusinessService;

import repast.yiyou.common.exception.BusinessException;

@Service
public class CartBusinessServiceImpl implements CartBusinessService {
	
	@Reference
	private ICartService cartService;
	@Reference
	private IOrderService orderService;

	@Override
	public void addCart(Long userId,String userName,String deskNum, Long goodsId, String auxIds, Integer count, String amount,String goodsType) {
	    //首先验证桌号，桌号存在就添加到当前桌的购物车
		//验证此用户是否存在购物车，存在则累加，不存在则新建购物车,：存在的商品项则累加，否则新建购物车项
		Cart cart=this.cartService.findCartByDeskNum(deskNum);
		if(cart==null) {
			cart=getCart(userId);
		}
		boolean itemFlag=true;
		boolean addFlag=true;//是否是累加
		BigDecimal all=new BigDecimal(0);
		if(cart==null) {
			cart=new Cart();
			cart.setMerchantId(ThreadContextHolder.getCurrentMerchantId());
			cart.setUserId(userId);
			cart.setDeskNum(deskNum);
			cart.setCreateTime(new Date());
			cart=this.cartService.save(cart);
		}else {
			Set<CartItem> items=cart.getItems();
			for(CartItem item:items) {
				if(item.getGoodsId().equals(goodsId)) {
					int initCount=item.getCount();//原始数量
					if(count==0) {
						this.cartService.removeCartItem(item.getId());
					}else {
						item.setCount(count);
						item.setAmount(new BigDecimal(amount).multiply(new BigDecimal(count)));
						this.cartService.updateCartItem(item);
					}
					itemFlag=false;//只更新购物车项数量和价格
					
					if(initCount>count) {
						addFlag=false;
						all=new BigDecimal(amount).multiply(new BigDecimal(initCount-count));
					}else {
						all=new BigDecimal(amount).multiply(new BigDecimal(count-initCount));
					}
				}
			}
		}
		if(itemFlag) {
			CartItem item=new CartItem();
			item.setUserId(userId);
			item.setUserName(userName);
			item.setGoodsId(goodsId);
			item.setAuxIds(auxIds);
			item.setCount(count);
			item.setGoodsAmount(new BigDecimal(amount));
			all=new BigDecimal(amount).multiply(new BigDecimal(count));
			item.setAmount(all);
			item.setCreateTime(new Date());
			item.setCart(cart);
			item.setGoodsType(goodsType);
			cartService.saveCartItem(item);
		}
		BigDecimal pre=cart.getAmount()==null?new BigDecimal(0):cart.getAmount();
		if(addFlag) {
			cart.setAmount(pre.add(all));
		}else {
			cart.setAmount(pre.subtract(all));
		}
		this.cartService.update(cart);
	}

	@Override
	public void updateCartItem(Long cartId, Long cartItemId, Integer count) {
		Cart cart=cartService.findCartById(cartId);
		if(cart==null)throw new BusinessException(4000, "cart is not be null");
		CartItem item=cartService.findCartItemById(cartItemId);
		if(item==null)throw new BusinessException(4000, "cartItem is not be null");
		
		item.setCount(count);
		item.setAmount(item.getAmount().multiply(new BigDecimal(count)));
		item.setCart(cart);
		this.cartService.updateCartItem(item);
		//更新购物车总价
	}

	@Override
	public void removeCartItem(Long cartId, Long cartItemId) {
	    cartService.removeCartItem(cartItemId);
	}

	@Override
	public Cart getCart(Long userId) {
		return cartService.findCart(userId);
	}
	
	@Override
	public Cart getCart(String deskNum) {
		return cartService.findCartByDeskNum(deskNum);
	}
	
	public Cart getCartById(Long id) {
		if(id==null)throw new BusinessException(4000, "id is not must be null");
		Cart cart=cartService.findCartById(id);
		if(cart==null)throw new BusinessException(4000, "cart query result is null");
		return cart;
	}

	@Override
	public void clearCart(Long id) {
		cartService.clearCart(id);
	}
	
	@Override
	public CartItemMap cartToMap(Cart cart){
		CartItemMap result=new CartItemMap();
		
		Map<String,Integer> typeMap=new HashMap<>();//分类map
		List<CartItemMap.ItemMap> list=new ArrayList<>();//订餐人统计
		Map<Long, List<CartItem>> map=new HashMap<>();//用户IDmap
		
		Map<Long, CartItemMap.ItemMap> extMap=new HashMap<>();
		Set<CartItem> set=cart.getItems();
		CartItemMap.ItemMap obj=null;
		for(CartItem item:set) {
			if(typeMap.containsKey(item.getGoodsType())) {
				typeMap.put(item.getGoodsType(), typeMap.get(item.getGoodsType())+1);
			}else {
				typeMap.put(item.getGoodsType(), 1);
			}
			List<CartItem> itemList=map.get(item.getUserId());
			if(itemList==null) {
				itemList=new ArrayList<>();
			}
			itemList.add(item);
			map.put(item.getUserId(),itemList);
			extMap.put(item.getUserId(),new CartItemMap.ItemMap(item.getUserId(),"",item.getUserName(),item.getCreateTime()));
		}
		for(Long uid:map.keySet()) {
			obj=new CartItemMap.ItemMap();
			CartItemMap.ItemMap ext=extMap.get(uid);
			obj.setUserId(uid);
			obj.setAvatar(ext.getAvatar());
			obj.setUserName(ext.getUserName());
			obj.setCreateTime(ext.getCreateTime());
			obj.setItemList(map.get(uid));
			list.add(obj);
		}
		result.setList(list);
		result.setTypeMap(typeMap);
		return result;
	}
}
