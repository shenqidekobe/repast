package com.yiyou.repast.merchant.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantAccountRepository;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.service.IMerchantAccountService;

@Service
public class MerchantAccountServiceImpl implements IMerchantAccountService{
	
	@Resource
	private MerchantAccountRepository merchantAccountRepository;

	@Override
	public MerchantAccount save(MerchantAccount obj) {
		obj.setCreateTime(new Date());
		return merchantAccountRepository.save(obj);
	}

	@Override
	public MerchantAccount update(MerchantAccount obj) {
		return merchantAccountRepository.save(obj);
	}

	@Override
	public MerchantAccount login(String loginName, String password) {
		return merchantAccountRepository.findByLoginNameAndPassword(loginName, password);
	}

	@Override
	public MerchantAccount find(Long id) {
		return merchantAccountRepository.findOne(id);
	}


}
