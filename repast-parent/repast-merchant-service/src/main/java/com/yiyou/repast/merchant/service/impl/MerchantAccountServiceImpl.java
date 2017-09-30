package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantAccountRepository;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.service.IMerchantAccountService;
import com.yiyou.repast.merchant.tools.PageConvertDataGrid;
import org.springframework.data.domain.*;
import org.springframework.util.StringUtils;
import repast.yiyou.common.util.DataGrid;
import repast.yiyou.common.util.Md5;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
	public MerchantAccount login(Long merchantId,String loginName, String password) {
		password=Md5.getMD5(password);
		return merchantAccountRepository.findByMerchantIdAndLoginNameAndPassword(merchantId,loginName, password);
	}

	@Override
	public MerchantAccount find(Long id) {
		return merchantAccountRepository.findOne(id);
	}

	@Override
	public List<MerchantAccount> findAll(Long merchantId) {
		return merchantAccountRepository.findAll();
	}

	@Override
	public DataGrid<MerchantAccount> findList(Long merchantId,String loginName, String status, String type, int page, int pageSize) {
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
	public MerchantAccount findByLoginName(Long merchantId,String loginName) {
		MerchantAccount account=new MerchantAccount();
		account.setLoginName(loginName);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantAccount> example = Example.of(account, matcher); 
		List<MerchantAccount> list=merchantAccountRepository.findAll(example);
		return list.isEmpty()?null:list.get(0);
	}

}
