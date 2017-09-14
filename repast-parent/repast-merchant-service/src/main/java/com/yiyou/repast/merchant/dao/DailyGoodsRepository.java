package com.yiyou.repast.merchant.dao;

import com.yiyou.repast.merchant.model.DailyGoods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyGoodsRepository extends JpaRepository<DailyGoods, Long> {

    List<DailyGoods> findAllByMerchantId(Long merchantId);

    DailyGoods findByMerchantIdAndId(Long merchantId, Long id);

    DailyGoods findByMerchantIdAndCreateTime(Long merchant, String data);
}
