package com.yiyou.repast.weixin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.service.IGoodsAuxService;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.weixin.service.GoodsBusinessService;

@Service
public class GoodsBusinessServiceImpl implements GoodsBusinessService {
	
	@Reference
	private IGoodsService goodsService;
	@Reference
	private IGoodsAuxService goodsAuxService;

	@Override
	public List<Goods> findGoodsList() {
		return goodsService.findAll(1l);
	}

	@Override
	public Goods findGoodsById(Long id) {
		Goods obj= goodsService.findById(1l, id);
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

}
