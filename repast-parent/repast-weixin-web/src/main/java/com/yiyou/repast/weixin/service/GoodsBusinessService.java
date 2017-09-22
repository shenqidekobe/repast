package com.yiyou.repast.weixin.service;

import java.util.List;
import java.util.Map;

import com.yiyou.repast.merchant.model.Goods;

/**
 * 商品服务接口
 * */
public interface GoodsBusinessService {
	
	/**
	 * 可购的全部商品列表
	 * */
	Map<String, List<Goods>> findGoodsList();
	
	/**
	 * 每日商品
	 * */
	Map<String, List<Goods>> findDailyGoodsList();
	
	/**
	 * 点击推荐商品
	 * */
	Map<String, List<Goods>> findRecommedGoodsList();
	
	/**
	 * 热销榜商品
	 * 只显示多少maxSize
	 * */
	Map<String, List<Goods>> findHotGoodsList(Long maxSize);
	
	/**
	 * 我点过的商品
	 * */
	Map<String, List<Goods>> findOldGoodsList(Long userId);

	/**
	 * 商品详情
	 * */
	Goods findGoodsById(Long id);
}
