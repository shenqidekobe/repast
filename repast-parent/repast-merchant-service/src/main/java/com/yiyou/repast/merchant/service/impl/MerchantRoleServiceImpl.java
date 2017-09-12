package com.yiyou.repast.merchant.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantMenuRepository;
import com.yiyou.repast.merchant.dao.MerchantRoleRepository;
import com.yiyou.repast.merchant.model.MerchantMenu;
import com.yiyou.repast.merchant.model.MerchantRole;
import com.yiyou.repast.merchant.service.IMerchantRoleService;

@Service
public class MerchantRoleServiceImpl implements IMerchantRoleService{
	
	@Resource
	private MerchantRoleRepository merchantRoleRepository;
	@Resource
	private MerchantMenuRepository merchantMenuRepository;

	@Override
	public MerchantRole find(Long id) {
		return merchantRoleRepository.findOne(id);
	}

	@Override
	public List<MerchantRole> findAll(Long merchantId) {
		return merchantRoleRepository.findAll();
	}

	@Override
	public MerchantRole save(MerchantRole obj) {
		return merchantRoleRepository.save(obj);
	}

	@Override
	public MerchantRole update(MerchantRole obj) {
		return merchantRoleRepository.save(obj);
	}

	@Override
	public MerchantRole delete(Long id) {
		merchantRoleRepository.delete(id);
		return null;
	}

	@Override
	public MerchantRole updatePermission(Long roleId, List<Long> menuIds) {
		MerchantRole role=merchantRoleRepository.findOne(roleId);
		Set<MerchantMenu> menus = new HashSet<>();
		for(Long menuId:menuIds) {
			MerchantMenu menu=merchantMenuRepository.findOne(menuId);
			menus.add(menu);
		}
		role.setMenus(menus);
		this.merchantRoleRepository.save(role);
		return role;
	}


}
