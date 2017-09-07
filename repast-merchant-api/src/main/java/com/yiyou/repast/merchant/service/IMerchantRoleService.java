package com.yiyou.repast.merchant.service;

import java.util.List;

import com.yiyou.repast.merchant.model.MerchantRole;

public interface IMerchantRoleService {
	
	MerchantRole find(Long id);
	
	List<MerchantRole> findAll(Long merchantId);

}
