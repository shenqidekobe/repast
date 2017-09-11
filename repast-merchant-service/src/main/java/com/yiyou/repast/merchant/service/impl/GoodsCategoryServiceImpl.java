package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.GoodsCategoryRepository;
import com.yiyou.repast.merchant.model.GoodsCategory;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {

    @Resource
    private GoodsCategoryRepository merchantGoodsCategoryRepository;


    @Override
    public void add(Long merchantId) {

    }

    @Override
    public GoodsCategory find(Long merchantId, Long GoodsCategoryId) {
        return null;
    }

    @Override
    public void remove(Long merchantId, Long GoodsCategoryId) {

    }

    @Override
    public GoodsCategory save(Long merchantId, GoodsCategory obj) {
        return null;
    }

    @Override
    public GoodsCategory update(Long merchantId, GoodsCategory obj) {
        return null;
    }

    @Override
    public List<GoodsCategory> findAll(Long merchantId) {
        return merchantGoodsCategoryRepository.findAll();
    }
}
