package com.yiyou.repast.merchant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantApplyRepository;
import com.yiyou.repast.merchant.model.MerchantApply;
import com.yiyou.repast.merchant.service.IMerchantApplyService;

@Service
public class MerchantApplyServiceImpl implements IMerchantApplyService {
	
	@Resource
	private MerchantApplyRepository merchantApplyRepository;

	@Override
	public MerchantApply findMerchantApplyByPath(String contentPath) {
		MerchantApply obj=new MerchantApply();
		obj.setApplyPath(contentPath);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantApply> example = Example.of(obj, matcher); 
	    List<MerchantApply> list=merchantApplyRepository.findAll(example);
		return list.isEmpty()?null:list.get(0);
	}

	@Override
	public MerchantApply findMerchantApplyByName(String applyName) {
		MerchantApply obj=new MerchantApply();
		obj.setApplyName(applyName);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantApply> example = Example.of(obj, matcher); 
	    List<MerchantApply> list=merchantApplyRepository.findAll(example);
		return list.isEmpty()?null:list.get(0);
	}

	@Override
	public MerchantApply findMerchantApplyByDomain(String domain) {
		MerchantApply obj=new MerchantApply();
		obj.setApplyDomain(domain);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantApply> example = Example.of(obj, matcher); 
	    List<MerchantApply> list=merchantApplyRepository.findAll(example);
		return list.isEmpty()?null:list.get(0);
	}

	@Override
	public List<MerchantApply> findAll() {
		return merchantApplyRepository.findAll();
	}

	@Override
	public MerchantApply findMerchantApplyByMerchantId(Long merchantId) {
		MerchantApply obj=new MerchantApply();
		obj.setMerchantId(merchantId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantApply> example = Example.of(obj, matcher); 
	    List<MerchantApply> list=merchantApplyRepository.findAll(example);
		return list.isEmpty()?null:list.get(0);
	}

}
