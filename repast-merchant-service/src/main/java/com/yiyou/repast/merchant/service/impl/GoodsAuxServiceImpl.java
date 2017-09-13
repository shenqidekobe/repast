package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.GoodsAuxRepository;
import com.yiyou.repast.merchant.model.GoodsAux;
import com.yiyou.repast.merchant.service.IGoodsAuxService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GoodsAuxServiceImpl implements IGoodsAuxService {


    @Resource
    GoodsAuxRepository goodsAuxRepository;


    @Override
    public List<GoodsAux> findAll(Long merchantId) {
        return goodsAuxRepository.findAllByMerchantId(merchantId);
    }

    @Override
    public GoodsAux save(Long merchantId, GoodsAux obj) {
        obj.setCreateTime(new Date());
        obj.setMerchantId(merchantId);
        return goodsAuxRepository.save(obj);
    }

    @Override
    public GoodsAux update(Long merchantId, GoodsAux obj) {
        return goodsAuxRepository.save(obj);
    }

    @Override
    public void remove(Long merchantId, Long GoodsSpecId) {
        goodsAuxRepository.delete(GoodsSpecId);
    }

    @Override
    public GoodsAux find(Long merchantId, Long id) {
        return goodsAuxRepository.findOne(id);
    }


}
