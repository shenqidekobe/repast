package com.yiyou.repast.weixin.service;

import com.yiyou.repast.pay.model.PaymentRecord;

public interface WechatBusinessService {
	
	PaymentRecord findByOrderId(Long orderId);
	
	void savePaymentRecord(PaymentRecord obj);

}
