package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.Goods;
import repast.yiyou.common.util.DataGrid;

import java.util.List;

/**
 * 商品
 */
public interface IGoodsService {

    void add(Long merchantId);

    Goods find(Long merchantId, Long goodsId);

    Goods findByGoodsName(Long merchantId, String goodsName);

    Goods save(Long merchantId, Goods obj);

    void remove(Long merchantId, Long goodsId);

    Goods update(Long merchantId, Goods obj);

    List<Goods> findAll(Long merchantId);

    DataGrid<Goods> findList(Long merchantId, int page, int pageSize);


}
