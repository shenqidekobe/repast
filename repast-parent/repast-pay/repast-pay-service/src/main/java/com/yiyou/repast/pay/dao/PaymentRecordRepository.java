package com.yiyou.repast.pay.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.pay.model.PaymentRecord;

public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Long>{

}
