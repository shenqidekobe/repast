package com.yiyou.repast.pay.service;

import com.yiyou.repast.pay.model.PaymentRecord;

public interface IPaymentRecordService {
	
	PaymentRecord save(PaymentRecord obj);

	PaymentRecord findByOrderId(Long orderId);
}
