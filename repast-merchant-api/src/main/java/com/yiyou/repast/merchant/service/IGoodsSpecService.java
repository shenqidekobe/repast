package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.GoodsSpec;

import java.util.List;

public interface IGoodsSpecService {

    List<GoodsSpec> findAll(Long merchantId);

    GoodsSpec save(Long merchantId, GoodsSpec obj);

    GoodsSpec update(Long merchantId, GoodsSpec obj);

    void remove(Long merchantId, Long GoodsSpecId);
}
