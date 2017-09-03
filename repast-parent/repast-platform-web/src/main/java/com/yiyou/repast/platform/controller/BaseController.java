package com.yiyou.repast.platform.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiyou.repast.platform.controller.base.GlobalDefine;
import com.yiyou.repast.platform.model.Admin;

public class BaseController {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	protected ObjectMapper objectMapper = new  ObjectMapper();
	
	
	/**
	 * 获取后台在线用户ID
	 * */
	protected Admin getAdminUserId(HttpServletRequest req) {
		Admin obj=(Admin) req.getSession().getAttribute(GlobalDefine.SESSION_LOGIN_ADMIN);
		return obj;
	}
	
	/**
	 * 获取参数map
	 * */
	@SuppressWarnings("rawtypes")
	public Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			Map properties = request.getParameterMap();
			Iterator entries = properties.entrySet().iterator();
			Map.Entry entry;
			String name = "";
			String value = "";
			while (entries.hasNext()) {
				value = "";
				entry = (Map.Entry) entries.next();
				name = (String) entry.getKey();
				Object valueObj = entry.getValue();
				if (null == valueObj) {
					value = "";
				} else if (valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					for (int i = 0; i < values.length; i++) {
						try {
							value += new String(
									values[i].getBytes("ISO-8859-1"), "UTF-8")
									+ ",";
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					value = value.substring(0, value.length() - 1);
				} else {
					value = valueObj.toString();
				}
				returnMap.put(name, value);
			}
		} catch (Exception e) {
		}

		return returnMap;
	}


	
}
