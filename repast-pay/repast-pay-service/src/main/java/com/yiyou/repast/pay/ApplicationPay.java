package com.yiyou.repast.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
public class ApplicationPay {
	
	public static void main(String[] args) {
		LoggerUtil.info("********************PAY repast pay service starting run*********************");
		SpringApplication.run(ApplicationPay.class, args);
		LoggerUtil.info("********************PAY repast pay service run end**************************");
	}
}

