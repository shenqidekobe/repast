package com.yiyou.repast.merchant.service;

import java.util.List;

import com.yiyou.repast.merchant.model.MerchantRole;

import repast.yiyou.common.util.DataGrid;

public interface IMerchantRoleService {
	
	MerchantRole save(MerchantRole obj);
	
	MerchantRole update(MerchantRole obj);
	
	MerchantRole delete(Long id);
	
	MerchantRole updatePermission(Long roleId,List<Long> menuIds);
	
	MerchantRole find(Long id);
	
	List<MerchantRole> findAll(Long merchantId);
	
	DataGrid<MerchantRole> findList(Long merchantId,String name,int page,int pageSize);

}
