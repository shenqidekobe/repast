package com.yiyou.repast.weixin.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.CartItem;
import com.yiyou.repast.order.service.ICartService;
import com.yiyou.repast.weixin.service.CartBusinessService;

import repast.yiyou.common.exception.BusinessException;

@Service
public class CartBusinessServiceImpl implements CartBusinessService {
	
	@Reference
	private ICartService cartService;

	@Override
	public void addCart(Long userId,String userName,String deskNum, Long goodsId, String auxIds, Integer count, String amount,String goodsType) {
	    //首先验证桌号，桌号存在就添加到当前桌的购物车
		//验证此用户是否存在购物车，存在则累加，不存在则新建购物车,：存在的商品项则累加，否则新建购物车项
		Cart cart=this.cartService.findCartByDeskNum(deskNum);
		if(cart==null) {
			cart=getCart(userId);
		}
		boolean itemFlag=true;
		BigDecimal all=new BigDecimal(0);
		if(cart==null) {
			cart=new Cart();
			cart.setUserId(userId);
			cart.setDeskNum(deskNum);
			cart.setCreateTime(new Date());
			this.cartService.save(cart);
		}else {
			Set<CartItem> items=cart.getItems();
			for(CartItem item:items) {
				if(item.getGoodsId().equals(goodsId)) {
					item.setCount(item.getCount()+1);
					all=item.getAmount().multiply(new BigDecimal(2));
					item.setAmount(all);
					this.cartService.updateCartItem(item);
					itemFlag=false;//只更新购物车项数量和价格
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
			all=new BigDecimal(amount);
			item.setAmount(all);
			item.setCreateTime(new Date());
			item.setCart(cart);
			item.setGoodsType(goodsType);
			cartService.saveCartItem(item);
		}
		BigDecimal pre=cart.getAmount()==null?new BigDecimal(0):cart.getAmount();
		cart.setAmount(pre.add(all));
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

}
