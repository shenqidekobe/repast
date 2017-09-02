package com.yiyou.repast.merchant.dubbo;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantRepository;
import com.yiyou.repast.merchant.model.Merchant;
import com.yiyou.repast.merchant.service.IMerchantService;

@Service(version = "1.0.0")
public class MerchantServiceImpl implements IMerchantService {
	
	@Resource
	private MerchantRepository merchantRepository;

	@Override
	public Merchant createMerchant(Merchant obj) {
		return merchantRepository.save(obj);
	}

	@Override
	public Merchant getById(Long id) {
		return merchantRepository.findOne(id);
	}

}
