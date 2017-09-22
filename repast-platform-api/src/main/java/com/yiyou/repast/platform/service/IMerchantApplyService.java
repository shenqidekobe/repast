package com.yiyou.repast.platform.service;

import java.util.List;

import com.yiyou.repast.platform.model.MerchantApply;

public interface IMerchantApplyService {
	
	/**
	 * 根据应用路径查询
	 * */
	MerchantApply findMerchantApplyByPath(String contentPath);
	
	/**
	 * 根据应用名查询
	 * */
	MerchantApply findMerchantApplyByName(String applyName);
	
	/**
	 * 根据应用域名查询
	 * */
	MerchantApply findMerchantApplyByDomain(String domain);
	
	/**
	 * 所有的应用
	 * */
	List<MerchantApply> findAll();

}
