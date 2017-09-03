package com.yiyou.repast.platform.service.impl;

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
import com.yiyou.repast.platform.dao.AdminRepository;
import com.yiyou.repast.platform.model.Admin;
import com.yiyou.repast.platform.service.IAdminService;

import repast.yiyou.common.util.EncryptUtil;

@Service
public class AdminService implements IAdminService{
	
	@Resource
	private AdminRepository adminRepository;

	@Override
	public Admin getById(Integer id) {
		return adminRepository.findOne(id);
	}


	@Override
	public Admin find(String name, String password) {
		password = EncryptUtil.getMD5(password);
		return adminRepository.findByLoginNameAndPassword(name, password);
	}

	@Override
	public boolean validateLoginName(String loginName) {
		Admin admin=new Admin();
		admin.setLoginName(loginName);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Admin> example = Example.of(admin, matcher); 
	    List<Admin> list=adminRepository.findAll(example);
		return list.isEmpty();
	}

	@Override
	public void removeAdmin(Integer id) {
		adminRepository.delete(id);
	}

	@Override
	public void modifyStatus(Integer id, String status) {
		Admin obj=adminRepository.findOne(id);
		obj.setStatus(status);
		adminRepository.save(obj);
	}

	@Override
	public List<Admin> getByGroupId(Integer groupId) {
		Admin admin=new Admin();
		admin.setGroupId(groupId.toString());
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Admin> example = Example.of(admin, matcher); 
	    List<Admin> list=adminRepository.findAll(example);
		return list;
	}

	@Override
	public Page<Admin> getAdminList(String loginName, String nickName, String status, int page,int size) {
		Admin admin=new Admin();
		if(!StringUtils.isEmpty(loginName))admin.setLoginName(loginName);
		if(!StringUtils.isEmpty(nickName))admin.setNickName(nickName);
		if(!StringUtils.isEmpty(status))admin.setStatus(status);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Admin> example = Example.of(admin, matcher); 
	    Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");  
		return adminRepository.findAll(example, pageable);
	}


	@Override
	public Admin save(Admin obj) {
		return adminRepository.save(obj);
	}


	@Override
	public Admin update(Admin obj) {
		return adminRepository.save(obj);
	}

}
