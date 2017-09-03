package com.yiyou.repast.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.platform.service.IAdminService;

import repast.yiyou.common.util.LoggerUtil;

@ComponentScan(basePackages={"com.yiyou.repast.platform.controller","com.yiyou.repast.platform.dubbo"})
@SpringBootApplication
public class Application {
	
	@Reference
	private IAdminService adminService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		LoggerUtil.info("platform WEB run..............");
	}
	
	@Bean
	public String login(){
		System.out.println("admin = "+adminService.find("admin", "11111"));
		return "aaa";
	}
}

