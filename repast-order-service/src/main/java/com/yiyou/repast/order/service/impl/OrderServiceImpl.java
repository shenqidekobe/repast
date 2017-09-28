package com.yiyou.repast.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.order.dao.OrderItemRepository;
import com.yiyou.repast.order.dao.OrderProcessRepository;
import com.yiyou.repast.order.dao.OrderRepository;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;
import com.yiyou.repast.order.model.OrderProcess;
import com.yiyou.repast.order.service.IOrderService;
import com.yiyou.repast.order.tools.PageConvertDataGrid;

import repast.yiyou.common.base.EnumDefinition.OrderProcessStatus;
import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.util.DataGrid;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Resource
	private OrderItemRepository orderItemRepository;
	@Resource
	private OrderRepository orderRepository;
	@Resource
	private OrderProcessRepository orderProcessRepository;

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

	@Override
	public DataGrid<Order> findOrderList(Long merchantId, String orderId, String deskNum, OrderStaus status,
			String startTime, String endTime, int page, int pageSize) {
		Order obj=new Order();
		if(merchantId!=null)obj.setMerchantId(merchantId);
		if(!StringUtils.isEmpty(orderId))obj.setOrderId(orderId);
		if(!StringUtils.isEmpty(deskNum))obj.setDeskNum(deskNum);
		if(status!=null)obj.setStatus(status);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Order> example = Example.of(obj, matcher); 
	    Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "id");  
		Page<Order> pages=orderRepository.findAll(example, pageable);
		return new PageConvertDataGrid.Bulid<Order>().page(pages).build().getData();
	}

	@Override
	public OrderItem updateOrderItem(OrderItem obj) {
		return orderItemRepository.save(obj);
	}

	@Override
	public OrderItem findItemById(Long itemId) {
		return orderItemRepository.findOne(itemId);
	}

	@Override
	public OrderProcess saveOrderProcess(OrderProcess obj) {
		obj.setCreateTime(new Date());
		obj.setStatus(OrderProcessStatus.await);
		return orderProcessRepository.save(obj);
	}

	@Override
	public OrderProcess updateOrderProcess(OrderProcess obj) {
		obj.setProcessTime(new Date());
		obj.setStatus(OrderProcessStatus.process);
		return orderProcessRepository.save(obj);
	}

	@Override
	public OrderProcess findOrderProcessById(Long id) {
		return orderProcessRepository.findOne(id);
	}

	@Override
	public OrderProcess findOrderProcessByOrderId(Long orderId) {
		OrderProcess obj=new OrderProcess();
		obj.setOrderId(orderId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<OrderProcess> example = Example.of(obj, matcher); 
		List<OrderProcess> list=orderProcessRepository.findAll(example);
		return CollectionUtils.isEmpty(list)?null:list.get(0);
	}

	@Override
	public List<OrderProcess> findOrderProcessAwaitList() {
		OrderProcess obj=new OrderProcess();
		obj.setStatus(OrderProcessStatus.await);;
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<OrderProcess> example = Example.of(obj, matcher); 
		List<OrderProcess> list=orderProcessRepository.findAll(example);
		return list;
	}


}
