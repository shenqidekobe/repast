package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.DailyGoods;

/**
 * 每日菜单
 */
public interface IDailyGoodsService extends IBaseService<DailyGoods>{

    DailyGoods findByDate(Long merchantId, String date);

}
