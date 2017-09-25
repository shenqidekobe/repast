package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.MerchantRoleMenu;

public interface IMerchantRoleMenuService extends IBaseService<MerchantRoleMenu> {
	
	void deleteByRoleId(Long roleId);

}
