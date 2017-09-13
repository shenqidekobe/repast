package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.GoodsRepository;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.service.IGoodsService;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {

    @Resource
    private GoodsRepository merchantGoodsRepository;

    @Override
    public Goods find(Long merchantId, Long goodsId) {
        return merchantGoodsRepository.findByMerchantIdAndId(merchantId,goodsId);
    }

    @Override
    public Goods findByGoodsName(Long merchantId, String goodsName) {
        return null;
    }

    @Override
    public Goods save(Long merchantId, Goods obj) {
        obj.setCreateTime(new Date());
        obj.setMerchantId(merchantId);
        return merchantGoodsRepository.save(obj);
    }

    @Override
    public void remove(Long merchantId, Long goodsId) {

    }

    @Override
    public Goods update(Long merchantId, Goods obj) {
        return merchantGoodsRepository.save(obj);
    }

    @Override
    public List<Goods> findAll(Long merchantId) {
        return merchantGoodsRepository.findAllByMerchantId(merchantId);
    }

    @Override
    public DataGrid<Goods> findList(Long merchantId, int page, int pageSize) {
        // TODO: 2017/9/12 分页
        return null;
    }


}
