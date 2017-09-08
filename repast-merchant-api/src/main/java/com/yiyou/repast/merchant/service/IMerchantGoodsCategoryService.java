package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.GoodsCategory;

import java.util.List;

/**
 * 商品分类
 */
public interface IMerchantGoodsCategoryService {

    void addGoodsCategory(Long merchantId);

    GoodsCategory findCategory(Long merchantId, Long GoodsCategoryId);

    GoodsCategory save(Long merchantId, GoodsCategory obj);

    GoodsCategory update(Long merchantId, GoodsCategory obj);

    List<GoodsCategory> findAll(Long merchantId);


}
