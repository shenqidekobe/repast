package com.yiyou.repast.pay.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

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

	@Override
	public PaymentRecord findByOrderId(Long orderId) {
		PaymentRecord account=new PaymentRecord();
		account.setOrderId(orderId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<PaymentRecord> example = Example.of(account, matcher); 
		List<PaymentRecord> list=paymentRecordRepository.findAll(example);
		return list.isEmpty()?null:list.get(0);
	}

}
