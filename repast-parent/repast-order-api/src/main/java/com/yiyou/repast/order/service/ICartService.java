package com.yiyou.repast.order.service;

import java.util.List;

import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.CartItem;

public interface ICartService {
	
	Cart save(Cart obj);
	
	Cart update(Cart obj);
	
	CartItem save(CartItem obj);
	
	CartItem update(CartItem obj);
	
	void removeCartItem(Long id);
	
	/**
	 * 清楚购物车
	 * */
	void clearCart(Long userId);
	
	/**
	 * 我的购物车
	 * */
	List<Cart> findCart(Long userId);

}
