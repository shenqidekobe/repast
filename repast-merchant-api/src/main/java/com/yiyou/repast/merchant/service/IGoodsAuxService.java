package com.yiyou.repast.merchant.service;

import java.util.List;

import com.yiyou.repast.merchant.model.GoodsAux;

/**
 * 商品
 */
public interface IGoodsAuxService  extends IBaseService<GoodsAux>{
	
	List<GoodsAux> findByIds(List<Long> ids);
}
