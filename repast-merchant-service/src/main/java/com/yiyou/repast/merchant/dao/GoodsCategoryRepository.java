package com.yiyou.repast.merchant.dao;

import com.yiyou.repast.merchant.model.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Long> {

    List<GoodsCategory> findByMerchantIdAndParentIsNullAndIdNot(Long merchantId, Long id);

    List<GoodsCategory> findByMerchantIdAndParentIsNull(Long merchantId);

    List<GoodsCategory> findAllByMerchantId(Long merchantId);

    GoodsCategory findByMerchantIdAndId(Long merchantId, Long id);

    @Transactional
    void removeByMerchantIdAndId(Long merchantId, Long id);
}
