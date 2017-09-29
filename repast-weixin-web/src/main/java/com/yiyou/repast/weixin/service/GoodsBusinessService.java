package com.yiyou.repast.weixin.service;

import java.util.List;
import java.util.Map;

import com.yiyou.repast.merchant.model.Goods;

import repast.yiyou.common.exception.BusinessException;

/**
 * 商品服务接口
 * */
public interface GoodsBusinessService {
	
	/**
	 * 可购的全部商品列表
	 * */
	Map<String, List<Goods>> findGoodsList()throws BusinessException;
	
	/**
	 * 每日商品
	 * */
	Map<String, List<Goods>> findDailyGoodsList()throws BusinessException;
	
	/**
	 * 点击推荐商品
	 * */
	Map<String, List<Goods>> findRecommedGoodsList()throws BusinessException;
	
	/**
	 * 热销榜商品
	 * 只显示多少maxSize
	 * */
	Map<String, List<Goods>> findHotGoodsList(Long maxSize)throws BusinessException;
	
	/**
	 * 我点过的商品
	 * */
	Map<String, List<Goods>> findOldGoodsList(Long userId)throws BusinessException;

	/**
	 * 商品详情
	 * */
	Goods findGoodsById(Long id)throws BusinessException;
}
