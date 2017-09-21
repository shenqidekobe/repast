package com.yiyou.repast.merchant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.UserWhiteRepository;
import com.yiyou.repast.merchant.model.UserWhite;
import com.yiyou.repast.merchant.service.IUserWhiteService;

import repast.yiyou.common.util.DataGrid;

@Service
public class UserWhiteServiceImpl implements IUserWhiteService{
	
	@Resource
	private UserWhiteRepository userWhiteRepository;

	@Override
	public UserWhite findById(Long merchantId, Long id) {
		return null;
	}

	@Override
	public List<UserWhite> findAll(Long merchantId) {
		return null;
	}

	@Override
	public UserWhite save(Long merchantId, UserWhite obj) {
		return null;
	}

	@Override
	public void remove(Long merchantId, Long id) {
		
	}

	@Override
	public UserWhite update(Long merchantId, UserWhite obj) {
		return null;
	}

	@Override
	public DataGrid<UserWhite> findList(Long merchantId, int page, int pageSize) {
		return null;
	}

}
