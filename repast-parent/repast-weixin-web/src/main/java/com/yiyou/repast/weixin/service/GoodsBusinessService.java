package com.yiyou.repast.weixin.service;

import java.util.List;

import com.yiyou.repast.merchant.model.Goods;

/**
 * 商品服务接口
 * */
public interface GoodsBusinessService {
	
	/**
	 * 可购商品列表
	 * */
	List<Goods> findGoodsList();

	/**
	 * 商品详情
	 * */
	Goods findGoodsById(Long id);
}
