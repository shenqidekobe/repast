package com.yiyou.repast.weixin.service;

import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.weixin.base.CartItemMap;

import repast.yiyou.common.exception.BusinessException;

public interface CartBusinessService {
	
	/**
	 * 添加商品到购物车
	 * */
	void addCart(Long userId,String userName,String deskNum,Long goodsId,
			String auxIds,Integer count,Integer peopleCount,
			String predictDate,String goodsType,Long specId,String specName)throws BusinessException;
	
	/**
	 * 修改购物车商品数量
	 * */
	void updateCartItem(Long cartId,Long cartItemId,Integer count)throws BusinessException;
	
	/**
	 * 删除购物车项
	 * */
	void removeCartItem(Long cartId,Long cartItemId)throws BusinessException;
	
	/**
	 * 获取用户的购物车内容
	 * */
	Cart getCart(Long userId)throws BusinessException;
	
	/**
	 * 获取桌号的购物车内容
	 * */
	Cart getCart(String deskNum)throws BusinessException;
	
	Cart getCartById(Long id)throws BusinessException;
	
	/**
	 * 清空购物车
	 * */
	void clearCart(Long id)throws BusinessException;
	
	/**
	 * 购物车项按用户分类转换成map
	 * */
	CartItemMap cartToMap(Cart cart)throws BusinessException;

}
