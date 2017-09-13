package com.yiyou.repast.merchant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.UserAuthorizeApplyRepository;
import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.merchant.service.IUserAuthorizeApplyService;

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
		List<UserAuthorizeApply> list=userAuthorizeApplyRepository.findByUserId(userId);
		return list.isEmpty()?null:list.get(0);
	}


}
