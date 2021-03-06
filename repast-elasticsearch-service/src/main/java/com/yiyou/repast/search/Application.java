package com.yiyou.repast.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		LoggerUtil.info("********************EC repast search service starting run*********************");
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("********************EC repast search service run end**************************");
	}
}

