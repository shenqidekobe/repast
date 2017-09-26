package com.yiyou.repast.weixin.service;

import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.weixin.base.CartItemMap;

public interface CartBusinessService {
	
	/**
	 * 添加商品到购物车
	 * */
	void addCart(Long userId,String userName,String deskNum,Long goodsId,
			String auxIds,Integer count,String amount,Integer peopleCount,String predictDate,String goodsType,String goodsName);
	
	/**
	 * 修改购物车商品数量
	 * */
	void updateCartItem(Long cartId,Long cartItemId,Integer count);
	
	/**
	 * 删除购物车项
	 * */
	void removeCartItem(Long cartId,Long cartItemId);
	
	/**
	 * 获取用户的购物车内容
	 * */
	Cart getCart(Long userId);
	
	/**
	 * 获取桌号的购物车内容
	 * */
	Cart getCart(String deskNum);
	
	Cart getCartById(Long id);
	
	/**
	 * 清空购物车
	 * */
	void clearCart(Long id);
	
	/**
	 * 购物车项按用户分类转换成map
	 * */
	CartItemMap cartToMap(Cart cart);

}
