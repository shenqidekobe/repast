package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.DailyGoodsRepository;
import com.yiyou.repast.merchant.model.DailyGoods;
import com.yiyou.repast.merchant.service.IDailyGoodsService;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DailyGoodsServiceImpl implements IDailyGoodsService {

    @Resource
    DailyGoodsRepository dailyGoodsRepository;

    @Override
    public List<DailyGoods> findAll(Long merchantId) {
        return dailyGoodsRepository.findAllByMerchantId(merchantId);
    }

    @Override
    public DailyGoods findById(Long merchantId, Long id) {
        return dailyGoodsRepository.findByMerchantIdAndId(merchantId,id);
    }

    @Override
    public List<DailyGoods> findByDate(Long mearchant, String date) {
        return dailyGoodsRepository.findDate(mearchant,date);
    }

    @Override
    public void deleteByDate(Long merchantId, String date) {
        dailyGoodsRepository.deleteDate(merchantId,date);
    }

    @Override
    public List<DailyGoods> save(Long merchantId, List<DailyGoods> obj) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-M-d");
        String date = sdf.format(new Date());
        for (DailyGoods dailyGoods : obj) {
            dailyGoods.setMerchantId(merchantId);
            dailyGoods.setCreateTime(date);
        }
        return dailyGoodsRepository.save(obj);
    }

    @Override
    public DailyGoods save(Long merchantId, DailyGoods obj) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-M-d");
        String date = sdf.format(new Date());
        obj.setMerchantId(merchantId);
        obj.setCreateTime(date);
        return dailyGoodsRepository.save(obj);
    }

    @Override
    public void remove(Long merchantId, Long GoodsSpecId) {
        dailyGoodsRepository.delete(GoodsSpecId);
    }

    @Override
    public DailyGoods update(Long merchantId, DailyGoods obj) {
        return null;
    }

    @Override
    public DataGrid<DailyGoods> findList(Long merchantId, int page, int pageSize) {
        return null;
    }


}
