package com.yiyou.repast.merchant.compent;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.SessionToken;
import com.yiyou.repast.merchant.model.MerchantLogs;
import com.yiyou.repast.merchant.service.IMerchantLogsService;

/**
 * 日志拦截保存
 */
@Aspect
@Component
public class WebLogAspect {

	@Reference
	private IMerchantLogsService merchantLogsService;

	@Pointcut(value = "execution(public * com.yiyou.repast.merchant.controller.*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		try {
			if(SecurityUtils.getSubject()==null)return;
			SessionToken session=(SessionToken) SecurityUtils.getSubject().getPrincipal();
			// 记录下请求内容
			String reqUrl = request.getRequestURL().toString();
			String methodName =joinPoint.getSignature().getDeclaringTypeName() + "."
					+ joinPoint.getSignature().getName();
			String params=JSON.json(request.getParameterMap());
			String ip=getIPAddress(request);
			MerchantLogs logs=new MerchantLogs();
			logs.setReqParams(params);
			logs.setIp(ip);
			logs.setMethodName(methodName);
			logs.setMerchantId(session.getMerchantId());
			logs.setOperUserId(session.getAccountId());
			logs.setOperUserName(session.getLoginName());
			logs.setReqUrl(reqUrl);
			this.merchantLogsService.save(session.getMerchantId(), logs);
		} catch (Exception e) {
		}
	}

	@AfterReturning("webLog()")
	public void doAfterReturning(JoinPoint joinPoint) {
		// 处理完请求，返回内容
	}

	/**
	 * 获取请求IP
	 */
	public static String getIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-real-ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
