package com.yiyou.repast.pay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.pay.dao.PaymentRecordRepository;
import com.yiyou.repast.pay.model.PaymentRecord;
import com.yiyou.repast.pay.service.IPaymentRecordService;
import org.springframework.data.domain.*;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentRecordServiceImpl implements IPaymentRecordService {

    @Resource
    private PaymentRecordRepository paymentRecordRepository;

    @Override
    public PaymentRecord save(PaymentRecord obj) {
        return paymentRecordRepository.save(obj);
    }

    @Override
    public PaymentRecord findByOrderId(Long orderId) {
        PaymentRecord account = new PaymentRecord();
        account.setOrderId(orderId);
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<PaymentRecord> example = Example.of(account, matcher);
        List<PaymentRecord> list = paymentRecordRepository.findAll(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public DataGrid<PaymentRecord> findList(Long merchantId, int page, int size) {
        //todo 查找
        Pageable pageable = new PageRequest(page, size);
        Page<PaymentRecord> pages = paymentRecordRepository.findAll(pageable);
        DataGrid<PaymentRecord> resulf = new DataGrid<>();
        resulf.setRecords(pages.getContent());
        resulf.setPageCount(pages.getTotalPages());
        resulf.setRowCount(pages.getTotalElements());
        return resulf;
    }

}
