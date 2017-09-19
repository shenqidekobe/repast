package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.GoodsAux;

import java.util.List;
import java.util.Set;

/**
 * 商品
 */
public interface IGoodsAuxService  extends IBaseService<GoodsAux>{
	
    Set<GoodsAux> findByIds(List<Long> ids);
}
