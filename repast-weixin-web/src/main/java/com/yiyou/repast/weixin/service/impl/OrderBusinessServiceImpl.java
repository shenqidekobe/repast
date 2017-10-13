package com.yiyou.repast.weixin.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.model.GoodsAux;
import com.yiyou.repast.merchant.model.UserAddress;
import com.yiyou.repast.merchant.service.IGoodsAuxService;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.merchant.service.IUserAddressService;
import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.CartItem;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;
import com.yiyou.repast.order.model.OrderProcess;
import com.yiyou.repast.order.service.IOrderService;
import com.yiyou.repast.weixin.base.ThreadContextHolder;
import com.yiyou.repast.weixin.service.OrderBusinessService;

import repast.yiyou.common.base.EnumDefinition.OrderProcessStatus;
import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.exception.BusinessException;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
	
	@Reference
	private IOrderService orderService;
	@Reference
	private IGoodsService goodsService;
	@Reference
	private IGoodsAuxService goodsAuxService;
	@Reference
	private IUserAddressService userAddressService;

	@Override
	public Order createOrder(Cart cart,String realName,String phone,String address)throws BusinessException {
		if(cart==null)throw new BusinessException(4444, "cart not must be null");
		if(cart.getItems()==null||cart.getItems().size()==0) {
			throw new BusinessException(4444, "cartItem not must be null");
		}
		boolean isNewOrder=false;
		//判断当前用户或者当前桌是否存在订单，不存在才添加，存在则追加
		Order order=this.getOrderByDeskNum(cart.getDeskNum());
		if(order==null&&StringUtils.isEmpty(cart.getDeskNum())) {
			order=this.getOrder(cart.getUserId());
		}
		if(order!=null) {
			Calendar c = Calendar.getInstance();  
		    c.add(Calendar.DAY_OF_MONTH, -1);  
		    c.set(Calendar.HOUR_OF_DAY, 23);
		    c.set(Calendar.MINUTE, 59);
		    c.set(Calendar.SECOND, 59);
			if(OrderStaus.settle.equals(order.getStatus())
					||OrderStaus.cancel.equals(order.getStatus())
					||order.getCreateTime().before(c.getTime())){
				order=null;//订单状态已结算或者已取消的，则创建新订单
			}
		}
		if(order==null) {
			isNewOrder=true;
			order=new Order();
			order.setUserId(cart.getUserId());
			order.setMerchantId(cart.getMerchantId());
			order.setDeskNum(cart.getDeskNum());
			order.setUserId(cart.getUserId());
			order.setPeopleCount(cart.getPeopleCount());
			order.setPredictDate(cart.getPredictDate());
			order.setAmount(new BigDecimal(0));
			order.setDiscountAmount(new BigDecimal(0));
			order.setCreateTime(new Date());
			order.setStatus(OrderStaus.await);
			order.setRealName(realName);
			order.setPhone(phone);
			order.setAddress(address);
			order=this.orderService.save(order);
			if(StringUtils.isNotEmpty(address)||StringUtils.isNotEmpty(phone)) {
				UserAddress ua=new UserAddress();
				ua.setRealName(realName);
				ua.setPhone(phone);
				ua.setAddress(address);
				ua.setUserId(cart.getUserId());
				userAddressService.save(ua);
			}
		}
		OrderItem oitem=null;
		BigDecimal total=order.getAmount();
		for(CartItem item:cart.getItems()) {
			oitem=new OrderItem();
			oitem.setOrder(order);
			oitem.setGoodsId(item.getGoodsId());
			oitem.setGoodsType(item.getGoodsType());
			oitem.setGoodsName(item.getGoodsName());
			oitem.setGoodsPic(item.getGoodsPic());
			oitem.setSpecId(item.getSpecId());
			oitem.setSpecName(item.getSpecName());
			oitem.setCount(item.getCount());
			oitem.setAmount(item.getAmount());
			if(StringUtils.isNotEmpty(item.getAuxIds())) {
				oitem.setAuxIds(item.getAuxIds());
				List<Long> auIds=Arrays.asList(item.getAuxIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
				List<GoodsAux> auxList=goodsAuxService.findByIds(auIds);
				if(!CollectionUtils.isEmpty(auxList)) {
					List<String> names=auxList.stream().map(GoodsAux::getName).collect(Collectors.toList());
					oitem.setAuxNames(names.isEmpty()?null:StringUtils.join(names.toArray(),","));
				}
			}
			oitem.setStatus(OrderStaus.await);
			oitem.setCreateTime(new Date());
			this.orderService.saveOrderItem(oitem);
			total=total.add(item.getAmount());
		}
		order.setAmount(total);
		if(isNewOrder) {
			String orderId="T"+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")+order.getId();
			order.setOrderId(orderId);
			//创建订单处理消息
			OrderProcess process=new OrderProcess();
			process.setOrderId(order.getId());
			process.setCreateTime(new Date());
			process.setMerchantId(order.getMerchantId());
			process.setStatus(OrderProcessStatus.await);
			this.orderService.saveOrderProcess(process);
		}
		this.orderService.update(order);
	
		return order;
	}

	@Override
	public Order getOrder(Long userId)throws BusinessException {
		List<Order> list=getOrderList(userId);
		return list.isEmpty()?null:list.get(list.size()-1);
	}
	
	@Override
	public Order getOrderById(Long id) throws BusinessException{
		return orderService.findById(id);
	}
	
	@Override
	public Order getOrderByDeskNum(String deskNum) throws BusinessException{
		List<Order> list=orderService.findByDeskNum(deskNum);
		return CollectionUtils.isEmpty(list)?null:list.get(list.size()-1);
	}

	@Override
	public List<Order> getOrderList(Long userId)throws BusinessException {
		if(userId==null)throw new BusinessException(4444, "userId not be null");
		
		return orderService.findByUserId(userId);
	}

	@Override
	public Order settleOrder(Order obj) throws BusinessException{
		if(obj==null||obj.getId()==0) {
			throw new BusinessException(4444, "Order not must be null");
		}
		obj=orderService.update(obj);
		//增加商品销量
		Set<OrderItem> items=obj.getItems();
		List<Goods> list=items.stream().map(o->goodsService.findById(ThreadContextHolder.getCurrentMerchantId(), o.getGoodsId()))
				.collect(Collectors.toList());
		for(Goods goods:list) {
			int sales=goods.getSales()==null?0:goods.getSales();
			goods.setSales(sales+1);
			this.goodsService.update(null, goods);
		}
		return obj;
	}

}
