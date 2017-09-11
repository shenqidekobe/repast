package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.GoodsRepository;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.service.IGoodsService;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.util.List;
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Resource
    private GoodsRepository merchantGoodsRepository;


    @Override
    public void add(Long merchantId) {

    }

    @Override
    public Goods find(Long merchantId, Long goodsId) {
        return null;
    }

    @Override
    public Goods findByGoodsName(Long merchantId, String goodsName) {
        return null;
    }

    @Override
    public Goods save(Long merchantId, Goods obj) {
        return null;
    }

    @Override
    public void remove(Long merchantId, Long goodsId) {

    }

    @Override
    public Goods update(Long merchantId, Goods obj) {
        return null;
    }

    @Override
    public List<Goods> findAll(Long merchantId) {
        return merchantGoodsRepository.findAll();
    }

    @Override
    public DataGrid<Goods> findList(Long merchantId, int page, int pageSize) {
        return null;
    }


}
