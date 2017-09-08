package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.Goods;
import repast.yiyou.common.util.DataGrid;

import java.util.List;

/**
 * 商品
 */
public interface IMerchantGoodsService {

    void addGoods(Long merchantId);

    Goods find(Long merchantId, Long goodsId);

    Goods findByGoodsName(Long merchantId, String goodsName);

    Goods save(Long merchantId, Goods obj);

    Goods update(Long merchantId, Goods obj);

    List<Goods> findAll(Long merchantId);

    DataGrid<Goods> findList(Long merchantId, String goodsName, String status, String type, int page, int pageSize);


}