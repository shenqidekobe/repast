package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.Merchant;

/**
 * 商户服务接口API
 * */
public interface IMerchantService {
	
	/**
	 * 创建商户信息
	 * */
	Merchant save(Merchant obj);
	
	/**
	 * 按ID查询商户
	 * */
	Merchant find(Long id);

}
