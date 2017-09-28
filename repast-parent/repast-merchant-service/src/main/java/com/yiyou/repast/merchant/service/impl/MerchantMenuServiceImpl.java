package com.yiyou.repast.merchant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantMenuRepository;
import com.yiyou.repast.merchant.model.MerchantMenu;
import com.yiyou.repast.merchant.service.IMerchantMenuService;

@Service
public class MerchantMenuServiceImpl implements IMerchantMenuService{
	
	@Resource
	private MerchantMenuRepository merchantMenuRepository;

	@Override
	public List<MerchantMenu> findAll(Long merchantId) {
		return merchantMenuRepository.findByMerchantIdOrderBySortAsc(merchantId);
	}


}
