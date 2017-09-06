package com.yiyou.repast.merchant.service.impl;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantMenuRepository;
import com.yiyou.repast.merchant.service.IMerchantMenuService;

@Service
public class MerchantMenuServiceImpl implements IMerchantMenuService{
	
	@Resource
	private MerchantMenuRepository merchantMenuRepository;


}
