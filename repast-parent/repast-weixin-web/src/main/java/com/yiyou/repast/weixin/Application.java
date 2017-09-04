package com.yiyou.repast.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		LoggerUtil.info("********************WEIXIN repast weixin web starting run*********************");
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("********************WEIXIN repast weixin web run end*********************");
	}
}

