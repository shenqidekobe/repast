package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.RecommendGoods;

import java.util.List;

/**
 * 推荐菜单
 */
public interface IRecommendService {

    RecommendGoods findById(Long merchantId, Long recommendId);

    void upDate(Long merchantId, List<Long> goodsIds);

    List<RecommendGoods> findAll(Long merchantId);

    void editRemark(Long merchantId, Long recommendId, String Remark);
}
