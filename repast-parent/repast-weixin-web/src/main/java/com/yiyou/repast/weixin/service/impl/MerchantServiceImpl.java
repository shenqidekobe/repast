package com.yiyou.repast.weixin.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.model.MerchantApply;
import com.yiyou.repast.merchant.service.IMerchantApplyService;
import com.yiyou.repast.weixin.base.Constants;
import com.yiyou.repast.weixin.service.MerchantService;

@org.springframework.stereotype.Service
public class MerchantServiceImpl implements MerchantService{
	
	@Reference
	private IMerchantApplyService merchantApplyService;

	@Override
	public MerchantApply getMerchantApply(String path) {
		if(StringUtils.isEmpty(path)) return new MerchantApply(Constants.merchantId,null);
		MerchantApply obj=merchantApplyService.findMerchantApplyByPath(path);
		if(obj==null) return new MerchantApply(Constants.merchantId,path);
		return obj;
	}

	@Override
	public List<MerchantApply> getAll() {
		return merchantApplyService.findAll();
	}


}
