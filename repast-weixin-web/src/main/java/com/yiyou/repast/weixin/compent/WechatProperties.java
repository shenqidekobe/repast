package com.yiyou.repast.weixin.compent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
public class WechatProperties {
	
	private String appId;
	private String appSecret;
	private String token;
	private String encodingAESKey;
	private String domain;
	private Boolean validateFlag;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEncodingAESKey() {
		return encodingAESKey;
	}
	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Boolean getValidateFlag() {
		return validateFlag;
	}
	public void setValidateFlag(Boolean validateFlag) {
		this.validateFlag = validateFlag;
	}
	
	

}
