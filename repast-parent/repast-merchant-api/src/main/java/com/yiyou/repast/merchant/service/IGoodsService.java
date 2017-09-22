package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.Goods;

import java.util.List;

/**
 * 商品
 */
public interface IGoodsService  extends IBaseService<Goods>{

    Goods findByGoodsName(Long merchantId, String goodsName);

    List<Goods> findByIds(Long merchantId, List<Long> ids);
}
