package com.yiyou.repast.weixin.service;

import java.util.List;

import com.yiyou.repast.platform.model.MerchantApply;

/**
 * 平台相关服务接口
 * */
public interface PlatformService {
	
	MerchantApply getMerchantApply(String path);

	List<MerchantApply> getAll();
}
