package com.yiyou.repast.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
public class ApplicationWebsite extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ApplicationWebsite.class);
	}

	public static void main(String[] args) {
		LoggerUtil.info("********************WEIXIN repast weixin web starting run*********************");
		SpringApplication.run(ApplicationWebsite.class, args);
		LoggerUtil.info("********************WEIXIN repast weixin web run end*********************");
	}
}
