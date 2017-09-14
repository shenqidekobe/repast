package com.yiyou.repast.merchant.dao;

import com.yiyou.repast.merchant.model.RecommendGoods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendGoodsRepository extends JpaRepository<RecommendGoods, Long> {

    List<RecommendGoods> findAllByMerchantId(Long merchantId);

    RecommendGoods findByMerchantIdAndId(Long merchant, Long id);
}
