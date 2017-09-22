package com.yiyou.repast.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.platform.model.MerchantApply;
import com.yiyou.repast.platform.service.IMerchantApplyService;
import com.yiyou.repast.weixin.service.PlatformService;

@org.springframework.stereotype.Service
public class PlatformServiceImpl implements PlatformService{
	
	@Reference
	private IMerchantApplyService merchantApplyService;

	@Override
	public MerchantApply getMerchantApply(String path) {
		if(StringUtils.isEmpty(path)) return new MerchantApply(1l,null);
		MerchantApply obj=merchantApplyService.findMerchantApplyByPath(path);
		if(obj==null) return new MerchantApply(1l,path);
		return obj;
	}


}
