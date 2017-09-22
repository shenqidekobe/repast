package com.yiyou.repast.weixin.compent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.dw.weixin.sdk.constants.WxEnumTransform.CALL_API_WAY;
import com.dw.weixin.sdk.openapi.IWeixinBasisAPI;
import com.dw.weixin.sdk.openapi.factory.WeixinAPIFactory;
import com.dw.weixin.sdk.openapi.factory.WeixinAPInitObject;
import com.yiyou.repast.platform.model.MerchantApply;
import com.yiyou.repast.weixin.service.PlatformService;

/**
 * 微信API启动初始化配置
 * */
@Configuration
public class WechatStartInit implements EnvironmentAware{
	
	@Resource
	private WechatProperties wechatProperties;
	@Resource
	private PlatformService platformService;
    
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
	
	@Bean
	public Map<String, Long> merchantAapplyInit(){
		Map<String, Long> applyMap=new HashMap<>();
		List<MerchantApply> list=platformService.getAll();
		if(CollectionUtils.isEmpty(list))return applyMap;
		applyMap=list.stream().collect(Collectors.toMap(MerchantApply::getApplyPath, MerchantApply::getMerchantId));
		System.out.println("applyMap  =  "+applyMap.toString());
		return applyMap;
	}

}
