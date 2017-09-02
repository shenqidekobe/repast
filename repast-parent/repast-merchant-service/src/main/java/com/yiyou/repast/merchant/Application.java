package com.yiyou.repast.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("李大侠开始发功了..............");
	}
}

