package com.yiyou.repast.merchant.dao;

import com.yiyou.repast.merchant.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findAllByMerchantId(Long maerchanId);

    Goods findByMerchantIdAndId(Long maerchanId, Long id);

}
