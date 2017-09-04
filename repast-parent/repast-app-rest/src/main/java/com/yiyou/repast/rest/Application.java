package com.yiyou.repast.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		LoggerUtil.info("********************repast rest app starting run*********************");
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("********************repast rest app run end*********************");
	}
}

