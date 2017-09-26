package com.yiyou.repast.merchant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yiyou.repast.merchant.model.DailyGoods;

public interface DailyGoodsRepository extends JpaRepository<DailyGoods, Long> {

    List<DailyGoods> findAllByMerchantId(Long merchantId);

    DailyGoods findByMerchantIdAndId(Long merchantId, Long id);

    @Query(value="select * from t_daily_goods  where merchant_id =?1 and DATE_FORMAT(create_time , '%Y-%m-%d' )= ?2",nativeQuery=true)
    List<DailyGoods> findByDate(Long merchant, String date);

    @Modifying
    @Transactional
    @Query("delete from DailyGoods  where merchantId = ?1 and DATE_FORMAT(createTime,'%Y-%m-%d')=?2")
    void deleteByDate(Long merchant, String date);
}

