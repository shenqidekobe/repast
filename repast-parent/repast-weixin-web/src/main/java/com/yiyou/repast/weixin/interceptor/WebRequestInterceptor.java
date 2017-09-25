package com.yiyou.repast.weixin.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yiyou.repast.weixin.base.Constants;

public class WebRequestInterceptor implements HandlerInterceptor {
	
	@Resource
	private Map<String, Long> cacheMap;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		String contentPath=request.getContextPath();
		request.setAttribute("_PATH",contentPath);
		Constants.merchantId=pathToId(contentPath);
		return true;
	}
	
	private Long pathToId(String contentPath) {
		Long id=cacheMap.get(contentPath);
		if(id==null) {
			contentPath=contentPath.startsWith("/")?contentPath.substring(1, contentPath.length()):contentPath;
			id=cacheMap.get(contentPath);
		}
		return id;
	}

}
