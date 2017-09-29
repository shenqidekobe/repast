package com.yiyou.repast.weixin.service;

import com.yiyou.repast.pay.model.PaymentRecord;

import repast.yiyou.common.exception.BusinessException;

public interface WechatBusinessService {
	
	PaymentRecord findByOrderId(Long orderId)throws BusinessException;
	
	void savePaymentRecord(PaymentRecord obj)throws BusinessException;

}
