package com.yiyou.repast.merchant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.merchant.model.MerchantDesk;

public interface MerchantDeskRepository extends JpaRepository<MerchantDesk, Long> {

    List<MerchantDesk> findByMerchantId(Long merchantId);

}

