package com.yiyou.repast.rest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
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
	
	private static final String sourceTime = "10:30-19:30";//每天推送的时间区间

	/**
	 * 实时推送订单,每隔30秒查询一次是否有新订单，每天规定时间段内推送
	 * */
	@Scheduled(cron = "*/30 * * * * ? ")
	public void push() {
		if(!timeSection())return;
		
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
	
	private boolean timeSection() {
		String curTime = DateFormatUtils.format(new Date(), "HH:mm");
		String[] args = sourceTime.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			long now = sdf.parse(curTime).getTime();
			long start = sdf.parse(args[0]).getTime();
			long end = sdf.parse(args[1]).getTime();
			if (args[1].equals("00:00")) {
				args[1] = "24:00";
			}
			boolean isSend = false;

			if (end < start) {
				if (now >= end && now < start) {
					isSend = false;
				} else {
					isSend = true;
				}
			} else {
				if (now >= start && now < end) {
					isSend = true;
				} else {
					isSend = false;
				}
			}
			return isSend;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
