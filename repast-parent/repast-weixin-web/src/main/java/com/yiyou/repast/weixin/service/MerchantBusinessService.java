package com.yiyou.repast.weixin.service;

import java.util.List;

import com.yiyou.repast.merchant.model.MerchantApply;

import repast.yiyou.common.exception.BusinessException;

/**
 * 商户相关服务接口
 * */
public interface MerchantBusinessService {
	
	MerchantApply getMerchantApply(String path)throws BusinessException;

	List<MerchantApply> getAll()throws BusinessException;
	
	String qrcode(Long merchantId,String deskNum)throws BusinessException;
	
	byte[] qrcodeImg(Long merchantId,String deskNum)throws BusinessException;
}
