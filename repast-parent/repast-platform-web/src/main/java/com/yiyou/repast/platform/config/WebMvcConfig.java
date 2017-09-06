package com.yiyou.repast.platform.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yiyou.repast.platform.filter.AdminInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Resource
	private AdminInterceptor adminInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
	}

}
