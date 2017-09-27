package com.yiyou.repast.weixin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.pay.model.PaymentRecord;
import com.yiyou.repast.pay.service.IPaymentRecordService;
import com.yiyou.repast.weixin.service.WechatBusinessService;

@Service
public class WechatBusinessServiceImpl implements WechatBusinessService{
	
	@Reference
	private IPaymentRecordService paymentRecordService;

	@Override
	public PaymentRecord findByOrderId(Long orderId) {
		return paymentRecordService.findByOrderId(orderId);
	}

	@Override
	public void savePaymentRecord(PaymentRecord obj) {
		obj.setPayTime(new Date());
		paymentRecordService.save(obj);
	}


}
