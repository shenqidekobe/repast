package com.yiyou.repast.merchant.dao;

import com.yiyou.repast.merchant.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findAllByMerchantId(Long maerchanId);

    Goods findByMerchantIdAndId(Long maerchanId, Long id);

    @Transactional
    void deleteByMerchantIdAndId(Long maerchanId, Long id);

    @Query("from Goods  where merchantId=?1 and id in ?2")
    List<Goods> findByIds(Long maerchanId, List<Long> ids);
}
