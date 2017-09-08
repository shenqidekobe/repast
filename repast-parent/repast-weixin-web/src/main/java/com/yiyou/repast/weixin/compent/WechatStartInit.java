package com.yiyou.repast.weixin.compent;

import javax.annotation.Resource;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.dw.weixin.sdk.constants.WxEnumTransform.CALL_API_WAY;
import com.dw.weixin.sdk.openapi.IWeixinBasisAPI;
import com.dw.weixin.sdk.openapi.factory.WeixinAPIFactory;
import com.dw.weixin.sdk.openapi.factory.WeixinAPInitObject;

/**
 * 微信API启动初始化配置
 * */
@Configuration
public class WechatStartInit implements EnvironmentAware{
	
	@Resource
	private WechatProperties wechatProperties;
    
	@Override
	public void setEnvironment(Environment env) {}
	
	@Bean
	public IWeixinBasisAPI weixinAPIinit()throws Exception{
		WeixinAPInitObject initObject=new WeixinAPInitObject();
		initObject.setAppId(wechatProperties.getAppId());
		initObject.setAppSecret(wechatProperties.getAppSecret());
		initObject.setAppToken(wechatProperties.getToken());
		initObject.setEncodingAesKey(wechatProperties.getEncodingAESKey());
		IWeixinBasisAPI wxAPI=WeixinAPIFactory.createIWeixinAPI(
				CALL_API_WAY.WEIXIN_PUBLIC, initObject);
		return wxAPI;
	}

}
