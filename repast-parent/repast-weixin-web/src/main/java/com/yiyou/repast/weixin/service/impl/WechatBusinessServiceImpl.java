package com.yiyou.repast.weixin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.pay.model.PaymentRecord;
import com.yiyou.repast.pay.service.IPaymentRecordService;
import com.yiyou.repast.weixin.service.WechatBusinessService;

import repast.yiyou.common.exception.BusinessException;

@Service
public class WechatBusinessServiceImpl implements WechatBusinessService{
	
	@Reference
	private IPaymentRecordService paymentRecordService;

	@Override
	public PaymentRecord findByOrderId(Long orderId) throws BusinessException{
		return paymentRecordService.findByOrderId(orderId);
	}

	@Override
	public void savePaymentRecord(PaymentRecord obj) throws BusinessException{
		obj.setPayTime(new Date());
		paymentRecordService.save(obj);
	}


}
