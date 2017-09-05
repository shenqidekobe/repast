package com.yiyou.repast.order.service.impl;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.order.dao.OrderItemRepository;
import com.yiyou.repast.order.dao.OrderRepository;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;
import com.yiyou.repast.order.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Resource
	private OrderItemRepository orderItemRepository;
	@Resource
	private OrderRepository orderRepository;

	@Override
	public Order save(Order obj) {
		return orderRepository.save(obj);
	}

	@Override
	public Order update(Order obj) {
		return orderRepository.save(obj);
	}

	@Override
	public OrderItem save(OrderItem obj) {
		return orderItemRepository.save(obj);
	}


}
