package com.yiyou.repast.weixin.service;

import java.util.List;

import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.Order;

import repast.yiyou.common.exception.BusinessException;

public interface OrderBusinessService {
	
	/**
	 * 创建新订单
	 * */
	Order createOrder(Cart cart)throws BusinessException;
	
	/**
	 * 订单结算
	 * */
	Order settleOrder(Order obj)throws BusinessException;
	
	/**
	 * 我的当前订单
	 * */
	Order getOrder(Long userId)throws BusinessException;
	
	Order getOrderById(Long id)throws BusinessException;
	
	Order getOrderByDeskNum(String deskNum)throws BusinessException;
	
	/**
	 * 我的订单列表
	 * */
	List<Order> getOrderList(Long userId);

}
