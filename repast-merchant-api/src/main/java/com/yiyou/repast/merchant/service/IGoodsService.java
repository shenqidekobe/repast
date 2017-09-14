package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.Goods;

/**
 * 商品
 */
public interface IGoodsService  extends IBaseService<Goods>{

    Goods findByGoodsName(Long merchantId, String goodsName);

}
