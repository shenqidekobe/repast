package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.GoodsCategoryRepository;
import com.yiyou.repast.merchant.model.GoodsCategory;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {

    @Resource
    private GoodsCategoryRepository goodsCategoryRepository;


    @Override
    public GoodsCategory find(Long merchantId, Long GoodsCategoryId) {
        return goodsCategoryRepository.findByMerchantIdAndId(merchantId, GoodsCategoryId);
    }

    @Override
    public void remove(Long merchantId, Long GoodsCategoryId) {
        goodsCategoryRepository.deleteByMerchantIdAndId(merchantId, GoodsCategoryId);
    }

    @Override
    public GoodsCategory save(Long merchantId, GoodsCategory obj) {
        obj.setCreateTime(new Date());
        obj.setMerchantId(merchantId);
        return goodsCategoryRepository.save(obj);
    }

    @Override
    public GoodsCategory update(Long merchantId, GoodsCategory obj) {
        return goodsCategoryRepository.save(obj);
    }

    @Override
    public List<GoodsCategory> findAll(Long merchantId) {
        return goodsCategoryRepository.findAllByMerchantId(merchantId);
    }

    @Override
    public List<GoodsCategory> findAllParent(Long merchantId) {
        return goodsCategoryRepository.findByMerchantIdAndParentIsNull(merchantId);
    }

    @Override
    public List<GoodsCategory> findAllParent(Long merchantId, Long id) {
        return goodsCategoryRepository.findByMerchantIdAndParentIsNullAndIdNot(merchantId, id);
    }

    @Override
    public DataGrid<GoodsCategory> findList(Long merchantId, int page, int pageSize) {
        List<GoodsCategory> datalist = findAll(merchantId);
        DataGrid<GoodsCategory> dataGrid = new DataGrid<>();
        dataGrid.setRecords(datalist);
        return dataGrid;
    }
}
