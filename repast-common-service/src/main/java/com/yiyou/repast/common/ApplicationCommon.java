package com.yiyou.repast.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repast.yiyou.common.util.LoggerUtil;

@SpringBootApplication
public class ApplicationCommon{

	public static void main(String[] args) {
		LoggerUtil.info("********************APP repast common service starting run*********************");
		SpringApplication.run(ApplicationCommon.class, args);
		LoggerUtil.info("********************APP repast common service starting end*********************");
	}
}

