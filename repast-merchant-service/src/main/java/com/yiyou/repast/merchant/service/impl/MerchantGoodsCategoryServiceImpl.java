package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantGoodsCategoryRepository;
import com.yiyou.repast.merchant.model.GoodsCategory;
import com.yiyou.repast.merchant.service.IMerchantGoodsCategoryService;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MerchantGoodsCategoryServiceImpl implements IMerchantGoodsCategoryService {

    @Resource
    private MerchantGoodsCategoryRepository merchantGoodsCategoryRepository;


    @Override
    public void addGoodsCategory(Long merchantId) {

    }

    @Override
    public GoodsCategory findCategory(Long merchantId, Long GoodsCategoryId) {
        return null;
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
