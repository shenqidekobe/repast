package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.DailyGoods;

import java.util.Date;
import java.util.List;

/**
 * 每日菜单
 */
public interface IDailyGoodsService {

    List<DailyGoods> findByDate(Long merchantId, Date date);

    void editByDate(Long merchantId, Date date, List<Long> goodsIds);

}
