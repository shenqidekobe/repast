package com.yiyou.repast.merchant.dao;

import com.yiyou.repast.merchant.model.GoodsSpec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsSpecRepository  extends JpaRepository<GoodsSpec,Long> {

    List<GoodsSpec> findAllByMerchantId(Long merchantId);

}
