package com.yiyou.repast.order.service;

import java.util.List;

import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;
import com.yiyou.repast.order.model.OrderProcess;

import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.util.DataGrid;

public interface IOrderService {
	
	Order save(Order obj);
	
	Order update(Order obj);
	
	void remove(Long id);
	
	OrderItem saveOrderItem(OrderItem obj);
	
	OrderItem updateOrderItem(OrderItem obj);
	
	OrderItem findItemById(Long itemId);
	
	Order findById(Long id);
	
	List<Order> findByUserId(Long userId);
	
	List<Order> findByDeskNum(String deskNum);
	
    OrderProcess saveOrderProcess(OrderProcess obj);
	
	OrderProcess updateOrderProcess(OrderProcess obj);
	
	OrderProcess findOrderProcessById(Long id);
	
	OrderProcess findOrderProcessByOrderId(Long orderId);
	
	List<OrderProcess> findOrderProcessAwaitList();
	
	DataGrid<Order> findOrderList(Long merchantId,String orderId,String deskNum,OrderStaus status,
			String startTime,String endTime,int page,int pageSize);
	
}
