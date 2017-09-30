package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.GoodsSpecRepository;
import com.yiyou.repast.merchant.model.GoodsSpec;
import com.yiyou.repast.merchant.service.IGoodsSpecService;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GoodsSpecServiceImpl implements IGoodsSpecService {

    @Resource
    GoodsSpecRepository goodsSpecRepository;

    @Override
    public GoodsSpec findById(Long mearchant, Long id) {
        return null;
    }

    @Override
    public List<GoodsSpec> findAll(Long merchantId) {
        return goodsSpecRepository.findAllByMerchantId(merchantId);
    }

    @Override
    public GoodsSpec save(Long merchantId, GoodsSpec obj) {
        obj.setCreateTime(new Date());
        obj.setMerchantId(merchantId);
        return goodsSpecRepository.save(obj);
    }

    @Override
    public GoodsSpec update(Long merchantId, GoodsSpec obj) {
        return goodsSpecRepository.save(obj);
    }

    @Override
    public DataGrid<GoodsSpec> findList(Long merchantId, int page, int pageSize) {
        return null;
    }

    @Override
    public void remove(Long merchantId, Long GoodsSpecId) {
        goodsSpecRepository.delete(GoodsSpecId);
    }

    @Override
    public Set<GoodsSpec> findByIds(List<Long> ids) {
        List<GoodsSpec> all = goodsSpecRepository.findAll(ids);
        Set<GoodsSpec> set = new HashSet<>();
        for (GoodsSpec goodsSpec : all) {
            set.add(goodsSpec);
        }
        return set;
    }
}
