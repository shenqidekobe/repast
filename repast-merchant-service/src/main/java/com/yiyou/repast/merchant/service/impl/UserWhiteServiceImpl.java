package com.yiyou.repast.merchant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

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
		return userWhiteRepository.findOne(id);
	}

	@Override
	public List<UserWhite> findAll(Long merchantId) {
		return userWhiteRepository.findAllByMerchantId(merchantId);
	}

	@Override
	public UserWhite save(Long merchantId, UserWhite obj) {
		return userWhiteRepository.save(obj);
	}

	@Override
	public void remove(Long merchantId, Long id) {
		userWhiteRepository.delete(id);
	}

	@Override
	public UserWhite update(Long merchantId, UserWhite obj) {
		return userWhiteRepository.save(obj);
	}

	@Override
	public DataGrid<UserWhite> findList(Long merchantId, int page, int pageSize) {
		return null;
	}

	@Override
	public UserWhite findUserWhiteByPhone(String phone) {
		UserWhite account=new UserWhite();
		account.setPhone(phone);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<UserWhite> example = Example.of(account, matcher); 
		List<UserWhite> list=userWhiteRepository.findAll(example);
		return list.isEmpty()?null:list.get(0);
	}

}
