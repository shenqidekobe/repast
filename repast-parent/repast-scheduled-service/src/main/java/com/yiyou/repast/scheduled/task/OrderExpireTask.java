package com.yiyou.repast.scheduled.task;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.service.ICartService;
import com.yiyou.repast.order.service.IOrderService;

import repast.yiyou.common.util.LoggerUtil;

@Component
public class OrderExpireTask {
	
	@Reference
	private ICartService cartService;
	@Reference
	private IOrderService orderService;

	@Scheduled(cron="0 0/30 * * * ?")
    public void executeOrderTask() {
		LoggerUtil.info("过期订单处理任务..............");
    }
	
	/**
	 * 每天凌晨30分全部清除
	 * */
	@Scheduled(cron = "0 30 0 * * ?")
    public void executeCartTask() {
		LoggerUtil.info("过期购物车处理任务..............");
		List<Cart> list=cartService.findAll(1l);
		if(!CollectionUtils.isEmpty(list)) {
			for(Cart c:list) {
				cartService.clearCart(c.getId());
			}
		}
    }
	
}
