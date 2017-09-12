package com.yiyou.repast.weixin.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.CartItem;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;
import com.yiyou.repast.order.service.IOrderService;
import com.yiyou.repast.weixin.service.OrderBusinessService;

import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.exception.BusinessException;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
	
	@Reference
	private IOrderService orderService;

	@Override
	public Order createOrder(Cart cart) {
		if(cart==null)throw new BusinessException(4444, "cart not be null");
		if(cart.getItems()==null||cart.getItems().size()==0) {
			throw new BusinessException(4444, "cartItem not be null");
		}
		Order order=new Order();
		order.setUserId(cart.getUserId());
		order.setDeskNum(cart.getDeskNum());
		order.setUserId(cart.getUserId());
		this.orderService.save(order);
		OrderItem oitem=null;
		BigDecimal total=new BigDecimal(0);
		for(CartItem item:cart.getItems()) {
			oitem=new OrderItem();
			oitem.setOrder(order);
			oitem.setGoodsId(item.getGoodsId());
			oitem.setCount(item.getCount());
			oitem.setAmount(item.getAmount());
			oitem.setAuxIds(item.getAuxIds());
			oitem.setStatus(OrderStaus.await);
			oitem.setCreateTime(new Date());
			this.orderService.saveOrderItem(oitem);
			total=total.add(item.getAmount());
		}
		String orderId="T"+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")+order.getId();
		order.setAmount(total);
		order.setOrderId(orderId);
		this.orderService.update(order);
		return order;
	}

	@Override
	public Order getOrder(Long userId) {
		List<Order> list=getOrderList(userId);
		return list.isEmpty()?null:list.get(list.size()-1);
	}

	@Override
	public List<Order> getOrderList(Long userId) {
		if(userId==null)throw new BusinessException(4444, "userId not be null");
		
		return orderService.findByUserId(userId);
	}

}
