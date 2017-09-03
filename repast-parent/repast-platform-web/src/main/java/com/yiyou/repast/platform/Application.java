package com.yiyou.repast.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication()
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("platform WEB run..............");
	}
	
}

