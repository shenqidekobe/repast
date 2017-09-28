package com.yiyou.repast.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import repast.yiyou.common.util.LoggerUtil;

@EnableScheduling
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		LoggerUtil.info("********************APP repast rest app starting run*********************");
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("********************APP repast rest app run end*********************");
	}
}

