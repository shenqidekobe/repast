package com.yiyou.repast.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import repast.yiyou.common.util.LoggerUtil;

/**
 * web run
 * */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
	
	public static void main(String[] args) {
		LoggerUtil.info("********************NOTIFY repast pay notify service starting run*********************");
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("********************NOTIFY repast pay notify service run end**************************");
	}
}

