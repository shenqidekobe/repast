package com.yiyou.repast.weixin.base;

public class ThreadContextHolder {

	private static final ThreadLocal<Long> merchant = new ThreadLocal<Long>();

	public static Long getCurrentMerchantId() {
		return merchant.get();
	}

	public static void setMerchantId(Long id) {
		merchant.set(id);
	}

}
