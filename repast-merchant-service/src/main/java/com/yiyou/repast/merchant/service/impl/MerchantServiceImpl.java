package com.yiyou.repast.merchant.service.impl;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantRepository;
import com.yiyou.repast.merchant.model.Merchant;
import com.yiyou.repast.merchant.service.IMerchantService;

@Service
public class MerchantServiceImpl implements IMerchantService {
	
	@Resource
	private MerchantRepository merchantRepository;

	@Override
	public Merchant save(Merchant obj) {
		return merchantRepository.save(obj);
	}

	@Override
	public Merchant find(Long id) {
		return merchantRepository.findOne(id);
	}

}
