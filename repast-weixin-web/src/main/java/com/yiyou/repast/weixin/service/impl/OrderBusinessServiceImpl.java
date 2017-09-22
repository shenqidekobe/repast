package com.yiyou.repast.weixin.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.service.IGoodsService;
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
	@Reference
	private IGoodsService goodsService;

	@Override
	public Order createOrder(Cart cart) {
		if(cart==null)throw new BusinessException(4444, "cart not must be null");
		if(cart.getItems()==null||cart.getItems().size()==0) {
			throw new BusinessException(4444, "cartItem not must be null");
		}
		//判断当前用户或者当前桌是否存在订单，不存在才添加，存在则追加
		Order order=this.getOrderByDeskNum(cart.getDeskNum());
		if(order==null&&StringUtils.isEmpty(cart.getDeskNum())) {
			order=this.getOrder(cart.getUserId());
		}
		if(order!=null) {
			if(!OrderStaus.settle.equals(order.getStatus())
					&&!OrderStaus.cancel.equals(order.getStatus())){
				order=null;//订单状态已结算或者已取消的，则创建新订单
			}
		}
		if(order==null) {
			order=new Order();
			order.setUserId(cart.getUserId());
			order.setDeskNum(cart.getDeskNum());
			order.setUserId(cart.getUserId());
			order.setPeopleCount(cart.getPeopleCount());
			order.setPredictDate(cart.getPredictDate());
			order.setAmount(new BigDecimal(0));
			order=this.orderService.save(order);
		}
		OrderItem oitem=null;
		BigDecimal total=order.getAmount();
		for(CartItem item:cart.getItems()) {
			oitem=new OrderItem();
			oitem.setOrder(order);
			oitem.setGoodsId(item.getGoodsId());
			oitem.setGoodsName(item.getGoodsName());
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
	public Order getOrderById(Long id) {
		return orderService.findById(id);
	}
	
	@Override
	public Order getOrderByDeskNum(String deskNum) {
		List<Order> list=orderService.findByDeskNum(deskNum);
		return list.isEmpty()?null:list.get(list.size()-1);
	}

	@Override
	public List<Order> getOrderList(Long userId) {
		if(userId==null)throw new BusinessException(4444, "userId not be null");
		
		return orderService.findByUserId(userId);
	}

	@Override
	public Order settleOrder(Order obj) {
		if(obj==null||obj.getId()==0) {
			throw new BusinessException(4444, "Order not must be null");
		}
		obj=orderService.update(obj);
		//增加商品销量
		Set<OrderItem> items=obj.getItems();
		List<Goods> list=items.stream().map(o->goodsService.findById(1l, o.getGoodsId()))
				.collect(Collectors.toList());
		for(Goods goods:list) {
			int sales=goods.getSales()==null?0:goods.getSales();
			goods.setSales(sales+1);
			this.goodsService.update(null, goods);
		}
		return obj;
	}

}
