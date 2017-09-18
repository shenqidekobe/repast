package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.GoodsSpec;

import java.util.List;
import java.util.Set;

/**
 * 商品规格
 */
public interface IGoodsSpecService extends IBaseService<GoodsSpec>{
    Set<GoodsSpec> findByIds(List<Long> ids);
}
