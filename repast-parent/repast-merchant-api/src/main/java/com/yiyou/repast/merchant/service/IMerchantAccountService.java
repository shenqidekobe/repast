package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.MerchantAccount;

public interface IMerchantAccountService {
	
	MerchantAccount save(MerchantAccount obj);
	
	MerchantAccount update(MerchantAccount obj);
	
	MerchantAccount login(String loginName,String password);
	
	MerchantAccount find(Long id);

}
