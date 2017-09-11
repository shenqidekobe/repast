package com.yiyou.repast.merchant.dao;

import com.yiyou.repast.merchant.model.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Long> {

    List<GoodsCategory> findByMerchantIdAndParentIsNull(Long merchantId);

    GoodsCategory findByMerchantIdAndId(Long merchantId, Long id);

}
