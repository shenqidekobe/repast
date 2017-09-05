package com.yiyou.repast.order.service;

import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;

public interface IOrderService {
	
	Order save(Order obj);
	
	Order update(Order obj);
	
	OrderItem save(OrderItem obj);

}
