package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.DailyGoodsRepository;
import com.yiyou.repast.merchant.dao.GoodsRepository;
import com.yiyou.repast.merchant.model.DailyGoods;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.service.IDailyGoodsService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DailyGoodsServiceImpl implements IDailyGoodsService {

    @Resource
    private DailyGoodsRepository dailyRepository;

    @Resource
    private GoodsRepository goodsRepository;

    @Override
    public List<DailyGoods> findByDate(Long merchantId, String date) {
        return dailyRepository.findByDate(merchantId, date);
    }

    @Override
    public void editByDate(Long merchantId, String date, List<Long> goodsIds) {
        dailyRepository.deleteByDate(merchantId, date);
        List<Goods> byIds = goodsRepository.findByIds(merchantId, goodsIds);
        List<DailyGoods> list = new ArrayList<>();
        for (Goods goods : byIds) {
            DailyGoods dailyGoods = new DailyGoods();
            dailyGoods.setAmount(goods.getAmount());
            dailyGoods.setGoods(goods);
            dailyGoods.setMerchantId(merchantId);
            dailyGoods.setCreateTime(new Date());
            list.add(dailyGoods);
        }
        dailyRepository.save(list);
    }
}
