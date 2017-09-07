package com.yiyou.repast.merchant.service.impl;

import java.util.Date;
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
import com.yiyou.repast.merchant.dao.MerchantAccountRepository;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.service.IMerchantAccountService;
import com.yiyou.repast.merchant.tools.PageConvertDataGrid;

import repast.yiyou.common.util.DataGrid;

@Service
public class MerchantAccountServiceImpl implements IMerchantAccountService{
	
	@Resource
	private MerchantAccountRepository merchantAccountRepository;

	@Override
	public MerchantAccount save(MerchantAccount obj) {
		obj.setCreateTime(new Date());
		return merchantAccountRepository.save(obj);
	}

	@Override
	public MerchantAccount update(MerchantAccount obj) {
		return merchantAccountRepository.save(obj);
	}

	@Override
	public MerchantAccount login(String loginName, String password) {
		return merchantAccountRepository.findByLoginNameAndPassword(loginName, password);
	}

	@Override
	public MerchantAccount find(Long id) {
		return merchantAccountRepository.findOne(id);
	}

	@Override
	public List<MerchantAccount> findAll() {
		return merchantAccountRepository.findAll();
	}

	@Override
	public DataGrid<MerchantAccount> findList(String loginName, String status, String type, int page, int pageSize) {
		MerchantAccount account=new MerchantAccount();
		if(!StringUtils.isEmpty(loginName))account.setLoginName(loginName);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantAccount> example = Example.of(account, matcher); 
	    Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "id");  
		Page<MerchantAccount> pages=merchantAccountRepository.findAll(example, pageable);
		return new PageConvertDataGrid.Bulid<MerchantAccount>().page(pages).build().getData();
	}

	@Override
	public void remove(Long id) {
		merchantAccountRepository.delete(id);
	}

	@Override
	public MerchantAccount findByLoginName(String loginName) {
		MerchantAccount account=new MerchantAccount();
		account.setLoginName(loginName);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantAccount> example = Example.of(account, matcher); 
		List<MerchantAccount> list=merchantAccountRepository.findAll(example);
		return list.isEmpty()?null:list.get(0);
	}


}
