package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.RecommendGoodsRepository;
import com.yiyou.repast.merchant.model.RecommendGoods;
import com.yiyou.repast.merchant.service.IRecommendService;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecommendServiceImpl implements IRecommendService {

    @Resource
    RecommendGoodsRepository recommendGoodsRepository;


    @Override
    public RecommendGoods findById(Long merchantId, Long id) {
        return null;
    }

    @Override
    public List<RecommendGoods> findAll(Long merchantId) {
        return null;
    }

    @Override
    public RecommendGoods save(Long merchantId, RecommendGoods obj) {
        return null;
    }

    @Override
    public void remove(Long merchantId, Long id) {

    }

    @Override
    public RecommendGoods update(Long merchantId, RecommendGoods obj) {
        return null;
    }

    @Override
    public DataGrid<RecommendGoods> findList(Long merchantId, int page, int pageSize) {
        return null;
    }
}
