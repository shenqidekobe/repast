package com.yiyou.repast.merchant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantRoleRepository;
import com.yiyou.repast.merchant.model.MerchantRole;
import com.yiyou.repast.merchant.service.IMerchantRoleService;

@Service
public class MerchantRoleServiceImpl implements IMerchantRoleService{
	
	@Resource
	private MerchantRoleRepository merchantRoleRepository;

	@Override
	public MerchantRole find(Long id) {
		return merchantRoleRepository.findOne(id);
	}

	@Override
	public List<MerchantRole> findAll(Long merchantId) {
		return merchantRoleRepository.findAll();
	}


}
