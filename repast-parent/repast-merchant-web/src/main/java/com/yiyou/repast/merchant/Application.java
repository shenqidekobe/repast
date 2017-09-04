package com.yiyou.repast.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
public class Application {
	
	
	public static void main(String[] args) {
		LoggerUtil.info("********************MERCHANT repast merchant web starting run*********************");
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("********************MERCHANT repast merchant web run end**************************");
	}


}
