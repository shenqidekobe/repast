package com.yiyou.repast.merchant.dao;

import com.yiyou.repast.merchant.model.DailyGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DailyGoodsRepository extends JpaRepository<DailyGoods, Long> {

    List<DailyGoods> findAllByMerchantId(Long merchantId);

    DailyGoods findByMerchantIdAndId(Long merchantId, Long id);

    @Query("FROM DailyGoods WHERE merchantId = ?1 AND createTime = ?2")
    List<DailyGoods> findDate(Long merchant, String date);

    @Modifying
    @Transactional
    @Query("DELETE  from DailyGoods  where merchantId = ?1 and createTime= ?2")
    void deleteDate(Long merchant, String date);
}

