package com.yiyou.repast.weixin.service;

import java.util.List;

import com.yiyou.repast.merchant.model.MerchantApply;

/**
 * 商户相关服务接口
 * */
public interface MerchantService {
	
	MerchantApply getMerchantApply(String path);

	List<MerchantApply> getAll();
	
	String qrcode(Long merchantId,String deskNum);
	
	byte[] qrcodeImg(Long merchantId,String deskNum);
}
