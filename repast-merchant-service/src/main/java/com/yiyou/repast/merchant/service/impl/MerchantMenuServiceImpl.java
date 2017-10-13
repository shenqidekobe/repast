package com.yiyou.repast.merchant.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
		List<MerchantMenu> list=merchantMenuRepository.findAll();
		return list.stream().sorted(Comparator.comparing(MerchantMenu::getSort)).collect(Collectors.toList());
	}


}
