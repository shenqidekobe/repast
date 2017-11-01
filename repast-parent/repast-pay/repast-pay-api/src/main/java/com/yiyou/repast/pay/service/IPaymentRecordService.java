package com.yiyou.repast.pay.service;

import com.yiyou.repast.pay.model.PaymentRecord;
import repast.yiyou.common.util.DataGrid;

public interface IPaymentRecordService {
	
	PaymentRecord save(PaymentRecord obj);

	PaymentRecord findByOrderId(Long orderId);

	DataGrid<PaymentRecord> findList(Long merchantId,int page, int size) ;
}
