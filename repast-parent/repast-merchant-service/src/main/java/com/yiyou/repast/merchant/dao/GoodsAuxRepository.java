package com.yiyou.repast.merchant.dao;

import com.yiyou.repast.merchant.model.GoodsAux;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsAuxRepository extends JpaRepository<GoodsAux,Long> {

    List<GoodsAux> findAllByMerchantId(Long merchantId);

    GoodsAux findByMerchantIdAndId(Long merchant, Long id);
}
