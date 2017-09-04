package com.yiyou.repast.rest.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.yiyou.repast.rest.interceptor.AppSafetyInterceptor;

@EnableAutoConfiguration
public class WebMvcConfig extends WebMvcAutoConfiguration {

	@Bean
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AppSafetyInterceptor()).addPathPatterns("/api/**");
	}

}
