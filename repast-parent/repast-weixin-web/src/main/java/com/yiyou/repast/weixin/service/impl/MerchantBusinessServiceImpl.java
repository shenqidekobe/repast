package com.yiyou.repast.weixin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.common.service.IZxingQrService;
import com.yiyou.repast.merchant.model.MerchantApply;
import com.yiyou.repast.merchant.service.IMerchantApplyService;
import com.yiyou.repast.weixin.base.ThreadContextHolder;
import com.yiyou.repast.weixin.service.MerchantBusinessService;

@org.springframework.stereotype.Service
public class MerchantBusinessServiceImpl implements MerchantBusinessService{
	
	@Reference
	private IMerchantApplyService merchantApplyService;
	@Reference
	private IZxingQrService zxingQrService;

	@Override
	public MerchantApply getMerchantApply(String path) {
		if(StringUtils.isEmpty(path)) return new MerchantApply(ThreadContextHolder.getCurrentMerchantId(),null);
		MerchantApply obj=merchantApplyService.findMerchantApplyByPath(path);
		if(obj==null) return new MerchantApply(ThreadContextHolder.getCurrentMerchantId(),path);
		return obj;
	}

	@Override
	public List<MerchantApply> getAll() {
		return merchantApplyService.findAll();
	}

	@Override
	public String qrcode(Long merchantId, String deskNum) {
		MerchantApply apply= merchantApplyService.findMerchantApplyByMerchantId(merchantId);
		String url=apply.getApplyUrl();
		url=url.concat("?param="+merchantId+"_"+deskNum);
		Map<String,Object> params=new HashMap<>();
		params.put("data", url);
		return zxingQrService.qrAsBase64(params);
	}
	
	@Override
	public byte[] qrcodeImg(Long merchantId, String deskNum) {
		MerchantApply apply= merchantApplyService.findMerchantApplyByMerchantId(merchantId);
		String url=apply.getApplyUrl();
		url=url.concat("?param="+merchantId+"_"+deskNum);
		Map<String,Object> params=new HashMap<>();
		params.put("data", url);
		return zxingQrService.qrAsByte(params);
	}


}
