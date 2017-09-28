package com.yiyou.repast.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.order.model.OrderProcess;
import com.yiyou.repast.order.service.IOrderService;
import com.yiyou.repast.rest.base.JpushService;
import com.yiyou.repast.rest.base.OnlineAccount;

import repast.yiyou.common.base.EnumDefinition.OrderProcessStatus;

/**
 * 订单推送服务
 * */
@Component
public class OrderPushService {

	@Reference
	private IOrderService orderService;

	/**
	 * 实时推送订单,每隔30秒查询一次是否有新订单，每天规定时间段内推送
	 * */
	@Scheduled(cron = "0/30 * 11-14 * * ?")
	public void push() {
		List<OrderProcess> list=orderService.findOrderProcessAwaitList();
		if(CollectionUtils.isEmpty(list))return;
		OnlineAccount.refershAll();
		
		String title="您有新的订单，点击查看";
		for(OrderProcess op:list) {
			op.setPushTime(new Date());
			op.setStatus(OrderProcessStatus.jpush);
			orderService.updateOrderProcess(op);
			//推送给所有的在线用户
			for(OnlineAccount account:OnlineAccount.getList()) {
				//推送终端用户接单
				Map<String, String> vMap= new HashMap<String, String>();
				vMap.put("orderId", op.getOrderId().toString());
				JpushService.pushMsg(account.getAccount(), title, vMap);
			}
		}
	}
	
}
