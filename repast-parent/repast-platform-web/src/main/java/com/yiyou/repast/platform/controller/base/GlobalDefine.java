package com.yiyou.repast.platform.controller.base;

public class GlobalDefine {
	
	
	/** 状态 1-有效 */
	public final static Integer STATUS_YES = 1;
	/** 状态 0-无效 */
	public final static Integer STATUS_NO = 0;

	/** 后台会话登陆用户 */
	public final static String SESSION_LOGIN_ADMIN = "admin_login";
	
	
	public final static String SERVER_DOMAIN_URL = "https://ry.taierquan.com/";//域名地址
	
	/**
	 * 存放图片的目录
	 */
	public final static String SAVEPATH = "/data/upload/mw/";
	
	public final static class JS_DEFINED {
		public final static class JS_RESULT{
			public static final String SUCCESS="success";
			public static final String ERROR="error";
			public static final String FAIL="fail";
			public static final String EXISTS="exists";
		}
	}

}
