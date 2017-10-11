package com.yiyou.repast.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
public class ApplicationOrder {
	
	public static void main(String[] args) {
		LoggerUtil.info("********************ORDER repast order service starting run*********************");
		SpringApplication.run(ApplicationOrder.class, args);
		LoggerUtil.info("********************ORDER repast order service run end**************************");
	}
}

