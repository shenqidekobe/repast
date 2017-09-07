package com.yiyou.repast.merchant.service;

import java.util.List;

import com.yiyou.repast.merchant.model.MerchantAccount;

import repast.yiyou.common.util.DataGrid;

public interface IMerchantAccountService {
	
	MerchantAccount save(MerchantAccount obj);
	
	MerchantAccount update(MerchantAccount obj);
	
	void remove(Long id);
	
	MerchantAccount login(String loginName,String password);
	
	MerchantAccount findByLoginName(String loginName);
	
	MerchantAccount find(Long id);
	
	List<MerchantAccount> findAll();
	
	DataGrid<MerchantAccount> findList(String loginName,String status,String type,int page,int pageSize);

}
