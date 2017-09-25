package com.yiyou.repast.weixin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.model.DailyGoods;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.model.RecommendGoods;
import com.yiyou.repast.merchant.service.IDailyGoodsService;
import com.yiyou.repast.merchant.service.IGoodsAuxService;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.merchant.service.IRecommendService;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;
import com.yiyou.repast.order.service.IOrderService;
import com.yiyou.repast.weixin.base.ThreadContextHolder;
import com.yiyou.repast.weixin.service.GoodsBusinessService;

@Service
public class GoodsBusinessServiceImpl implements GoodsBusinessService {
	
	@Reference
	private IGoodsService goodsService;
	@Reference
	private IGoodsAuxService goodsAuxService;
	@Reference
	private IRecommendService recommendService;
	@Reference
	private IDailyGoodsService dailyGoodsService;
	@Reference
	private IOrderService orderService;

	@Override
	public Map<String, List<Goods>> findGoodsList() {
		Map<String, List<Goods>> result=new HashMap<>();
		List<Goods> list=goodsService.findAll(ThreadContextHolder.getCurrentMerchantId());
		if(CollectionUtils.isEmpty(list))return result;
		result=list.stream().collect(Collectors.groupingBy(Goods::getCategoryName));
		return result;
	}

	@Override
	public Goods findGoodsById(Long id) {
		Goods obj= goodsService.findById(ThreadContextHolder.getCurrentMerchantId(), id);
		if(StringUtils.isNotEmpty(obj.getAuxIds())) {
			List<String> ids=Arrays.asList(obj.getAuxIds().split(","));
			List<Long> outputIds =new ArrayList<>();
			CollectionUtils.collect(ids, 
		       new Transformer(){
		         public java.lang.Object transform(java.lang.Object input){
		           return new Long((String)input);
		         }
		      } ,outputIds );
			obj.setAuxs(this.goodsAuxService.findByIds(outputIds));
		}
		return obj;
	}

	@Override
	public Map<String, List<Goods>> findDailyGoodsList() {
		Map<String, List<Goods>> result=new HashMap<>();
		List<DailyGoods> dailtList=dailyGoodsService.findAll(ThreadContextHolder.getCurrentMerchantId());
		if(CollectionUtils.isEmpty(dailtList))return result;
		//将RecommendGoods对象的Goods属性转换成list
		List<Goods> list=dailtList.stream().map(DailyGoods::getGoods).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(list))return result;
		//将Goods集合按分类分组
		result=list.stream().collect(Collectors.groupingBy(Goods::getCategoryName));
		return result;
	}

	@Override
	public Map<String, List<Goods>> findRecommedGoodsList() {
		Map<String, List<Goods>> result=new HashMap<>();
		List<RecommendGoods> remList=recommendService.findAll(ThreadContextHolder.getCurrentMerchantId());
		if(CollectionUtils.isEmpty(remList))return result;
		//将RecommendGoods对象的Goods属性转换成list
		List<Goods> list=remList.stream().map(RecommendGoods::getGoods).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(list))return result;
		//将Goods集合按分类分组
		result=list.stream().collect(Collectors.groupingBy(Goods::getCategoryName));
		return result;
	}

	@Override
	public Map<String, List<Goods>> findHotGoodsList(Long maxSize) {
		//热销榜，查询购买数量最多的商品
		Map<String, List<Goods>> result=new HashMap<>();
		List<Goods> list=goodsService.findAll(ThreadContextHolder.getCurrentMerchantId());
		if(CollectionUtils.isEmpty(list))return result;
		//销量倒叙，最多取出maxSize条专程list，再将list进行分组
		result=list.stream().sorted(Comparator.comparing(Goods::getSales).reversed()).limit(maxSize)
		.collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Goods::getCategoryName));
		return result;
	}

	@Override
	public Map<String, List<Goods>> findOldGoodsList(Long userId) {
		Map<String, List<Goods>> result=new HashMap<>();
		List<Order> orderList=orderService.findByUserId(userId);
		if(CollectionUtils.isEmpty(orderList))return result;
		//将Order集合的商品ID集合提取出来，并且去重
		Set<Long> goodsIdList=orderList.stream().flatMap(or->or.getItems().stream()).collect(Collectors.toSet())
		                       .stream().map(OrderItem::getGoodsId).distinct().collect(Collectors.toSet());
		if(CollectionUtils.isEmpty(goodsIdList))return result;
		
		//id集合查询得到商品列表
		List<Goods> list=goodsIdList.stream().map(o->goodsService.findById(ThreadContextHolder.getCurrentMerchantId(), o.longValue()))
				.collect(Collectors.toList());
		if(CollectionUtils.isEmpty(list))return result;
		
		//将Goods集合按分类分组
		result=list.stream().collect(Collectors.groupingBy(Goods::getCategoryName));
		return result;
	}

}
