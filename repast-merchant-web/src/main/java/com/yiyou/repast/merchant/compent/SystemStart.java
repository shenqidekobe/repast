package com.yiyou.repast.merchant.compent;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yiyou.repast.merchant.base.Constants;

/**
 * 系统启动初始化
 * */
@Component
public class SystemStart implements InitializingBean{

	@Value("${current.merchant.id}")
    private Long merchantId;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Constants.MERCHANT_ID=merchantId;
	}


}
