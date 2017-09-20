package com.yiyou.repast.weixin.service;

import java.util.List;

import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.Order;

public interface OrderBusinessService {
	
	/**
	 * 创建新订单
	 * */
	Order createOrder(Cart cart);
	
	/**
	 * 我的当前订单
	 * */
	Order getOrder(Long userId);
	
	Order getOrderById(Long id);
	
	Order getOrderByDeskNum(String deskNum);
	
	/**
	 * 我的订单列表
	 * */
	List<Order> getOrderList(Long userId);

}
