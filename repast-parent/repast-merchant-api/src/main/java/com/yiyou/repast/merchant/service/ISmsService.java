package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.Sms;

public interface ISmsService extends IBaseService<Sms> {

	/**
	 * 发送短信
	 * */
	int sendMessage(String mobile, String content);

	/**
	 * 验证短信内容
	 * */
	boolean verifyCode(String mobile, String code);

}
