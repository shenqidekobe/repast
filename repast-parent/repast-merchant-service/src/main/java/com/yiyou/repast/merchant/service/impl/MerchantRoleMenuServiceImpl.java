package com.yiyou.repast.merchant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantRoleMenuRepository;
import com.yiyou.repast.merchant.model.MerchantRoleMenu;
import com.yiyou.repast.merchant.service.IMerchantRoleMenuService;

import repast.yiyou.common.util.DataGrid;

@Service
public class MerchantRoleMenuServiceImpl implements IMerchantRoleMenuService{
	
	@Resource
	private MerchantRoleMenuRepository merchantRoleMenuRepository;

	@Override
	public MerchantRoleMenu findById(Long merchantId, Long id) {
		return null;
	}

	@Override
	public List<MerchantRoleMenu> findAll(Long merchantId) {
		return null;
	}

	@Override
	public MerchantRoleMenu save(Long merchantId, MerchantRoleMenu obj) {
		return null;
	}

	@Override
	public void remove(Long merchantId, Long id) {
	}

	@Override
	public MerchantRoleMenu update(Long merchantId, MerchantRoleMenu obj) {
		return null;
	}

	@Override
	public DataGrid<MerchantRoleMenu> findList(Long merchantId, int page, int pageSize) {
		return null;
	}

	@Override
	public void deleteByRoleId(Long roleId) {
		merchantRoleMenuRepository.deleteByRoleId(roleId);
	}

}
