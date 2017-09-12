package com.yiyou.repast.merchant.service;

import java.util.List;

import com.yiyou.repast.merchant.model.MerchantMenu;

public interface IMerchantMenuService {
	
	List<MerchantMenu> findAll(Long merchantId);

}
