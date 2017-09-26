package com.yiyou.repast.common.service;

import java.util.Map;

public interface IZxingQrService {
	
	/**
	 * 生成base64的二维码
	 * */
	String qrAsBase64(Map<String,Object> params);
	
	/**
	 * 生成二进制的二维码
	 * */
	byte[] qrAsByte(Map<String,Object> params);

}
