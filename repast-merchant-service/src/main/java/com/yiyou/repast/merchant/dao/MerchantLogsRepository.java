package com.yiyou.repast.merchant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.merchant.model.MerchantLogs;

public interface MerchantLogsRepository extends JpaRepository<MerchantLogs, Long> {

	List<MerchantLogs> findByMerchantId(Long merchantId);
}

