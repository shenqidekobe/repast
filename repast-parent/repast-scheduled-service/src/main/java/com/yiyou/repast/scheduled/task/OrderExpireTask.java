package com.yiyou.repast.scheduled.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import repast.yiyou.common.util.LoggerUtil;

@Component
public class OrderExpireTask {

	@Scheduled(cron="0 0/2 * * * ?")
    public void executeFileDownLoadTask() {
		LoggerUtil.info("过期订单处理任务..............");
    }
	
}
