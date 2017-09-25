package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.GoodsRepository;
import com.yiyou.repast.merchant.dao.RecommendGoodsRepository;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.model.RecommendGoods;
import com.yiyou.repast.merchant.service.IRecommendService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecommendServiceImpl implements IRecommendService {

    @Resource
    RecommendGoodsRepository recommendGoodsRepository;

    @Resource
    GoodsRepository goodsRepository;

    @Transactional
    @Override
    public void upDate(Long merchantId, List<Long> goodsIds) {
        recommendGoodsRepository.removeAllByMerchantId(merchantId);
        List<Goods> byIds = goodsRepository.findByIds(merchantId, goodsIds);
        if (byIds.size()>0) {
            List<RecommendGoods> list =new ArrayList<>();
            for (Goods byId : byIds) {
                RecommendGoods rg =new RecommendGoods();
                rg.setGoods(byId);
                rg.setCreateTime(new Date());
                list.add(rg);
            }
            recommendGoodsRepository.save(list);
        }
    }

    @Override
    public List<RecommendGoods> findAll(Long merchantId) {
        return recommendGoodsRepository.findAllByMerchantId(merchantId);
    }

}
