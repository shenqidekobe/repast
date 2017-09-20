package com.yiyou.repast.order.service;

import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.CartItem;

public interface ICartService {
	
	Cart save(Cart obj);
	
	Cart update(Cart obj);
	
	CartItem saveCartItem(CartItem obj);
	
	CartItem updateCartItem(CartItem obj);
	
	Cart findCartById(Long id);
	
	Cart findCartByDeskNum(String deskNum);
	
	CartItem findCartItemById(Long id);
	
	void removeCartItem(Long id);
	
	/**
	 * 清楚购物车
	 * */
	void clearCart(Long id);
	
	/**
	 * 我的购物车
	 * */
	Cart findCart(Long userId);

}
