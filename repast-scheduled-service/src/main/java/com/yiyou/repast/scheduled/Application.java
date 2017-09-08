package com.yiyou.repast.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
@EnableScheduling
public class Application {
	
	public static void main(String[] args) {
		LoggerUtil.info("********************SCHEDULED repast scheduled service starting run*********************");
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("********************SCHEDULED repast scheduled service run end**************************");
	}
}

