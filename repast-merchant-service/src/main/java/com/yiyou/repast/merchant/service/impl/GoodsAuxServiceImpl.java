package com.yiyou.repast.merchant.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.GoodsAuxRepository;
import com.yiyou.repast.merchant.model.GoodsAux;
import com.yiyou.repast.merchant.service.IGoodsAuxService;

import repast.yiyou.common.util.DataGrid;

@Service
public class GoodsAuxServiceImpl implements IGoodsAuxService {


    @Resource
    GoodsAuxRepository goodsAuxRepository;


    @Override
    public GoodsAux findById(Long mearchant, Long id) {
        return goodsAuxRepository.findByMerchantIdAndId(mearchant, id);
    }

    @Override
    public List<GoodsAux> findAll(Long merchantId) {
        return goodsAuxRepository.findAllByMerchantId(merchantId);
    }

    @Override
    public DataGrid<GoodsAux> findList(Long merchantId, int page, int pageSize) {
        return null;
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
    public List<GoodsAux> findByIds(List<Long> ids) {
    	return goodsAuxRepository.findAll(ids);
    }
}
