package com.yiyou.repast.common.qrcode;

public class Base64 {

    public static String encodeToString(byte[] src){
    	return java.util.Base64.getEncoder().encodeToString(src);
    }
}
