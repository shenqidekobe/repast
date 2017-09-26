package com.yiyou.repast.weixin.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yiyou.repast.weixin.interceptor.WebRequestInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Resource
	private WebRequestInterceptor webRequestInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webRequestInterceptor).addPathPatterns("/**").
		            excludePathPatterns("/wx/oauth/**");
	}

}
