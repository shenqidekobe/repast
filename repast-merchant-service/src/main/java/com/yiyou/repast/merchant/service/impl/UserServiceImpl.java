package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.UserRepository;
import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.merchant.service.IUserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
	
	@Resource
	private UserRepository userRepository;

	@Override
	public List<User> findAll(Long merchantId) {
		return userRepository.findAll();
	}

	@Override
	public User save(Long merchantId, User obj) {
		return userRepository.save(obj);
	}

	@Override
	public void remove(Long merchantId, Long id) {
		userRepository.delete(id);
	}

	@Override
	public User update(Long merchantId, User obj) {
		return userRepository.save(obj);
	}

	@Override
	public DataGrid<User> findList(Long merchantId, int page, int pageSize) {
		return null;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long merchantId, Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User findByOpenId(String openId) {
		User account=new User();
		account.setOpenId(openId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<User> example = Example.of(account, matcher); 
		return userRepository.findOne(example);
	}

}
