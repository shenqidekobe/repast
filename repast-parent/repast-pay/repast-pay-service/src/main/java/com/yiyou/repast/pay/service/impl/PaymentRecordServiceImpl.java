package com.yiyou.repast.pay.service.impl;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.pay.dao.PaymentRecordRepository;
import com.yiyou.repast.pay.model.PaymentRecord;
import com.yiyou.repast.pay.service.IPaymentRecordService;

@Service
public class PaymentRecordServiceImpl implements IPaymentRecordService{

	@Resource
	private PaymentRecordRepository paymentRecordRepository;
	
	@Override
	public PaymentRecord save(PaymentRecord obj) {
		return paymentRecordRepository.save(obj);
	}

}
