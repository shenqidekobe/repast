package com.yiyou.repast.platform.config;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.yiyou.repast.platform.filter.AdminInterceptor;

@EnableAutoConfiguration
public class WebConfig extends WebMvcAutoConfiguration {

	@Resource
	private AdminInterceptor adminInterceptor;

	@Bean
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
	}

}
