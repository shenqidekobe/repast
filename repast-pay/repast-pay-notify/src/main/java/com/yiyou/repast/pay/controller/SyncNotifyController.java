package com.yiyou.repast.pay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付异步通知入口
 * */
@RestController
public class SyncNotifyController {
	
	
	@PostMapping("/repast/payment/notify")
	public String execute(HttpServletRequest req) {
		
		return "success";
	}

}
