package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantGoodsRepository;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.service.IMerchantGoodsService;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MerchantGoodsServiceImpl implements IMerchantGoodsService {

    @Resource
    private MerchantGoodsRepository merchantGoodsRepository;

    @Override
    public void addGoods(Long merchantId) {

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
    public Goods update(Long merchantId, Goods obj) {
        return null;
    }

    @Override
    public List<Goods> findAll(Long merchantId) {
        return merchantGoodsRepository.findAll();
    }

    @Override
    public DataGrid<Goods> findList(Long merchantId, String goodsName, String status, String type, int page, int pageSize) {
        return null;
    }

}
