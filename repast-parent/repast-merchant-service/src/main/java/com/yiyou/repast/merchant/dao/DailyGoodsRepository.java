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

    @Query("from DailyGoods  where merchantId =?1 and DATE_FORMAT( createTime , '%Y-%c-%e' )= ?2")
    List<DailyGoods> findByDate(Long merchant, String date);

    @Modifying
    @Transactional
    @Query("delete from DailyGoods  where merchantId = ?1 and DATE_FORMAT(createTime,'%Y-%c-%e')= ?2")
    void deleteByDate(Long merchant, String date);
}

