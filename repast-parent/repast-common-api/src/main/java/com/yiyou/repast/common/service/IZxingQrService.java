package com.yiyou.repast.common.service;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public interface IZxingQrService {
	
	/**
	 * 生成base64的二维码
	 * */
	String qrAsBase64(Map<String,String> params);
	
	/**
	 * 生成二进制的二维码
	 * */
	ByteArrayOutputStream qrAsByte(Map<String,String> params);

}
