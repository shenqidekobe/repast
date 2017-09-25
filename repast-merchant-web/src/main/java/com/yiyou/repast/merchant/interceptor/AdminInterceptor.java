package com.yiyou.repast.merchant.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.ThreadContextHolder;

public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		request.setAttribute("_PATH", request.getContextPath());
		Object object=request.getSession().getAttribute(Constants.SESSION_MERCHANTID_KEY);
		if(object!=null){
			Long merchantId=(Long) object;
			ThreadContextHolder.setMerchantId(merchantId);
		}
		return true;
	}

}
