package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.GoodsCategory;

import java.util.List;

/**
 * 商品分类
 */
public interface IGoodsCategoryService extends IBaseService<GoodsCategory> {

    List<GoodsCategory> findAllParent(Long merchantId);

    List<GoodsCategory> findAllParent(Long merchantId,Long id);
}
