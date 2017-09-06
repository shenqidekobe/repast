package com.yiyou.repast.merchant.service;

import java.util.List;

import com.yiyou.repast.merchant.model.MerchantAccount;

import repast.yiyou.common.util.DataGrid;

public interface IMerchantAccountService {
	
	MerchantAccount save(MerchantAccount obj);
	
	MerchantAccount update(MerchantAccount obj);
	
	MerchantAccount login(String loginName,String password);
	
	MerchantAccount find(Long id);
	
	List<MerchantAccount> findAll();
	
	DataGrid<MerchantAccount> findList(String loginName,String status,String type,int page,int pageSize);

}
