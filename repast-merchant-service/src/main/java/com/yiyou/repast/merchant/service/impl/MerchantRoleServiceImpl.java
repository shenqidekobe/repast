package com.yiyou.repast.merchant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantMenuRepository;
import com.yiyou.repast.merchant.dao.MerchantRoleMenuRepository;
import com.yiyou.repast.merchant.dao.MerchantRoleRepository;
import com.yiyou.repast.merchant.model.MerchantMenu;
import com.yiyou.repast.merchant.model.MerchantRole;
import com.yiyou.repast.merchant.model.MerchantRoleMenu;
import com.yiyou.repast.merchant.service.IMerchantRoleService;
import com.yiyou.repast.merchant.tools.PageConvertDataGrid;

import repast.yiyou.common.util.DataGrid;

@Service
public class MerchantRoleServiceImpl implements IMerchantRoleService{
	
	@Resource
	private MerchantRoleRepository merchantRoleRepository;
	@Resource
	private MerchantMenuRepository merchantMenuRepository;
	@Resource
	private MerchantRoleMenuRepository merchantRoleMenuRepository;

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
		merchantRoleMenuRepository.deleteByRoleId(roleId);
		
		MerchantRole role=merchantRoleRepository.findOne(roleId);
		MerchantRoleMenu ru=null;
		for(Long menuId:menuIds) {
			ru=new MerchantRoleMenu();
			ru.setRole(role);
			MerchantMenu menu=merchantMenuRepository.findOne(menuId);
			ru.setMenu(menu);
			merchantRoleMenuRepository.save(ru);
		}
		return role;
	}

	@Override
	public DataGrid<MerchantRole> findList(Long merchantId, String name, int page, int pageSize) {
		MerchantRole role=new MerchantRole();
		if(!StringUtils.isEmpty(name))role.setName(name);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantRole> example = Example.of(role, matcher); 
	    Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "id");  
		Page<MerchantRole> pages=merchantRoleRepository.findAll(example, pageable);
		return new PageConvertDataGrid.Bulid<MerchantRole>().page(pages).build().getData();
	}

	@Override
	public MerchantRole findByName(Long merchantId, String name) {
		MerchantRole role=new MerchantRole();
		role.setName(name);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantRole> example = Example.of(role, matcher); 
		List<MerchantRole> list=merchantRoleRepository.findAll(example);
		return list.isEmpty()?null:list.get(0);
	}


}
