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
	private String domain; //与微信服务器通信的服务器域名
	private String imgDomain;//存储菜单图片域名地址
	private Boolean validateFlag;//是否需要验证微信服务器
	private String noticeTemplateId;//模版通知的消息ID
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
	public String getImgDomain() {
		return imgDomain;
	}
	public void setImgDomain(String imgDomain) {
		this.imgDomain = imgDomain;
	}
	public Boolean getValidateFlag() {
		return validateFlag;
	}
	public void setValidateFlag(Boolean validateFlag) {
		this.validateFlag = validateFlag;
	}
	public String getNoticeTemplateId() {
		return noticeTemplateId;
	}
	public void setNoticeTemplateId(String noticeTemplateId) {
		this.noticeTemplateId = noticeTemplateId;
	}

}
