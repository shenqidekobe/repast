package com.yiyou.repast.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.StringUtils;

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
	public OrderItem saveOrderItem(OrderItem obj) {
		return orderItemRepository.save(obj);
	}

	@Override
	public void remove(Long id) {
		orderRepository.delete(id);
	}

	@Override
	public Order findById(Long id) {
		return orderRepository.findOne(id);
	}

	@Override
	public List<Order> findByUserId(Long userId) {
		Order obj=new Order();
		obj.setUserId(userId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Order> example = Example.of(obj, matcher); 
		return orderRepository.findAll(example);
	}
	
	@Override
	public List<Order> findByDeskNum(String deskNum){
		if(StringUtils.isEmpty(deskNum))return null;
		Order obj=new Order();
		obj.setDeskNum(deskNum);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Order> example = Example.of(obj, matcher); 
		return orderRepository.findAll(example);
	}


}