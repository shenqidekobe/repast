package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.GoodsSpec;

import java.util.List;

/**
 * 商品规格
 */
public interface IGoodsSpecService {

    List<GoodsSpec> findAll(Long merchantId);

    List<GoodsSpec> findByGoodsId(Long merchantId, Long goodsId);

    GoodsSpec save(Long merchantId, GoodsSpec obj);

    GoodsSpec update(Long merchantId, GoodsSpec obj);

    void remove(Long merchantId, Long GoodsSpecId);
}
