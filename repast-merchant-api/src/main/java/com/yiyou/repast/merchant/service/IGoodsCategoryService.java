package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.GoodsCategory;
import repast.yiyou.common.util.DataGrid;

import java.util.List;

/**
 * 商品分类
 */
public interface IGoodsCategoryService {

    GoodsCategory find(Long merchantId, Long GoodsCategoryId);

    void remove(Long merchantId, Long GoodsCategoryId);

    GoodsCategory save(Long merchantId, GoodsCategory obj);

    GoodsCategory update(Long merchantId, GoodsCategory obj);

    List<GoodsCategory> findAll(Long merchantId);

    List<GoodsCategory> findAllParent(Long merchantId);

    List<GoodsCategory> findAllParent(Long merchantId,Long id);

    DataGrid<GoodsCategory> findList(Long merchantId, int page, int pageSize);

}
