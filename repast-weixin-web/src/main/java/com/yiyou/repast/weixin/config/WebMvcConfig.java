package com.yiyou.repast.weixin.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.yiyou.repast.weixin.interceptor.WebRequestInterceptor;

@EnableAutoConfiguration
public class WebMvcConfig extends WebMvcAutoConfiguration {

	@Bean
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WebRequestInterceptor()).addPathPatterns("/wx/**");
	}

}
