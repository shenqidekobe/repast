package com.yiyou.repast.rest.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yiyou.repast.rest.base.AppResult;
import com.yiyou.repast.rest.base.LogHelper;

import repast.yiyou.common.util.EncryptUtil;
import repast.yiyou.common.util.LoggerUtil;

/**
 * APP接口安全拦截器
 * 控制接口的并发量
 * */
public class AppSafetyInterceptor implements HandlerInterceptor{
	
	
	private static final String APP_SING_KEY="ksd826sdAJn25h128sdjxqah6dl0J6";
	public static final String SIGN_PARAMETER="verifySign";//签名参数key
	private static Gson gson=new Gson();
	private static final Semaphore semap = new Semaphore(50);//并发量50

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse rsp, Object arg2, Exception arg3)
			throws Exception {
		String contextPath = req.getContextPath();
		if (req.getRequestURI().startsWith(contextPath + "/api/")) {
			semap.release();//处理完释放连接
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView mav)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp, Object arg2) throws Exception {
		String contextPath = req.getContextPath();
		if (req.getRequestURI().startsWith(contextPath + "/api/")) {
			//验证APP请求接口
			Enumeration<String> keys = req.getParameterNames();
			Map<String,String> paramMap=new HashMap<String,String>();
			String reqSign="";
			while(keys.hasMoreElements()) {
			    String key = keys.nextElement();
			    String value=req.getParameter(key);
			    if(SIGN_PARAMETER.equals(key)){
			    	reqSign=value;
	        		continue;
	        	}
			    paramMap.put(key, value);
			}
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(paramMap.entrySet());  
	        //排序方法  
	        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
	            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {   
	                return (o1.getKey()).toString().compareTo(o2.getKey());
	            }
	        });
	        StringBuffer sb=new StringBuffer();
	        for(Map.Entry<String, String> m : infoIds){
	        	if(sb.length()>0){
	        		sb.append("&");
	        	}
	        	sb.append(m.getKey()+"=").append(m.getValue());
	        }
	        
	        if(StringUtils.isEmpty(sb.toString())){
	        	 sb.append(APP_SING_KEY);
	        }else{
	        	 sb.append("&" + APP_SING_KEY);
	        }
	        String sign=EncryptUtil.getMD5(sb.toString());
	        String host = req.getRemoteHost();
			String requestURL = req.getRequestURL().toString();
			String method = req.getMethod();
	        String logs=LogHelper.print(LogHelper.titleOutput("MEDICAL REQUEST LOGGING"),
					String.format("RequestURL: %s", requestURL),
					String.format("LocalRequestQuery: %s", sb.toString()),
					String.format("Method: %s", method),
					String.format("Sign: %s", reqSign+"  equals  "+sign),
					String.format("Host: %s", host), String.format("RequestParameters: %s", paramMap.toString()));
	        LoggerUtil.info("\n" + logs + "\n");
	      
	        if(!sign.equals(reqSign)){
	        	AppResult result = new AppResult();
	        	result.setCode(AppResult.SIGN_FIAL);
	        	result.setMsg("签名失败");
	        	rsp.getWriter().write(gson.toJson(result));
	        	return false;
	        }
	        semap.acquire();//获取一个连接
	        return true;
		}
		return true;
	}
	
}
