package com.yiyou.repast.order.service;

import java.util.List;

import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;

public interface IOrderService {
	
	Order save(Order obj);
	
	Order update(Order obj);
	
	void remove(Long id);
	
	OrderItem saveOrderItem(OrderItem obj);
	
	Order findById(Long id);
	
	List<Order> findByUserId(Long userId);
	
}
