package com.yiyou.repast.merchant.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.UserAddressRepository;
import com.yiyou.repast.merchant.model.UserAddress;
import com.yiyou.repast.merchant.service.IUserAddressService;

@Service
public class UserAddressServiceImpl implements IUserAddressService {
	
	@Resource
	private UserAddressRepository userAddressRepository;

	@Override
	public List<UserAddress> findByUserId(Long userId) {
		UserAddress account=new UserAddress();
		account.setUserId(userId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<UserAddress> example = Example.of(account, matcher); 
		List<UserAddress> list=userAddressRepository.findAll(example);
		if(!CollectionUtils.isEmpty(list)) {
			list=list.stream().sorted(Comparator.comparing(UserAddress::getSort)).collect(Collectors.toList());
		}
		return list;
	}

	@Override
	public UserAddress save(UserAddress obj) {
		obj.setSort(1);
		obj.setCreateTime(new Date());
		return userAddressRepository.save(obj);
	}

	@Override
	public UserAddress getById(Long id) {
		return userAddressRepository.findOne(id);
	}

	@Override
	public void remove(Long id) {
		userAddressRepository.delete(id);
	}

	@Override
	public UserAddress update(UserAddress obj) {
		obj.setSort(0);
		return userAddressRepository.save(obj);
	}


}
