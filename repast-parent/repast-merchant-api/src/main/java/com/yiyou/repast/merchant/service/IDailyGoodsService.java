package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.DailyGoods;

import java.util.List;

/**
 * 每日菜单
 */
public interface IDailyGoodsService extends IBaseService<DailyGoods>{

    List<DailyGoods> findByDate(Long merchantId, String date);

}
