package com.yiyou.repast.merchant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.UserAuthorizeApplyRepository;
import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.merchant.service.IUserAuthorizeApplyService;

import repast.yiyou.common.util.DataGrid;

@Service
public class UserAuthorizeApplyServiceImpl implements IUserAuthorizeApplyService {
	
	@Resource
	private UserAuthorizeApplyRepository userAuthorizeApplyRepository;

	@Override
	public UserAuthorizeApply save(UserAuthorizeApply obj) {
		return userAuthorizeApplyRepository.save(obj);
	}

	@Override
	public UserAuthorizeApply update(UserAuthorizeApply obj) {
		return userAuthorizeApplyRepository.save(obj);
	}

	@Override
	public UserAuthorizeApply findById(Long id) {
		return userAuthorizeApplyRepository.findOne(id);
	}

	@Override
	public UserAuthorizeApply findByUserId(Long userId) {
		List<UserAuthorizeApply> list=userAuthorizeApplyRepository.findByUserIdOrderByIdDesc(userId);
		return list.isEmpty()?null:list.get(0);
	}

	@Override
	public UserAuthorizeApply findById(Long merchantId, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAuthorizeApply> findAll(Long merchantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAuthorizeApply save(Long merchantId, UserAuthorizeApply obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long merchantId, Long id) {
		
	}

	@Override
	public UserAuthorizeApply update(Long merchantId, UserAuthorizeApply obj) {
		return null;
	}

	@Override
	public DataGrid<UserAuthorizeApply> findList(Long merchantId, int page, int pageSize) {
		return null;
	}


}
