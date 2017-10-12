package com.yiyou.repast.rest.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录授权session
 * */
public class SessionToken implements Serializable{
	
	private static final long serialVersionUID = 348051708546226611L;
	private Long accountId;
	private String account;
	private Long merchantId;//商户ID
	private String merchantName;//商户名称
	private String industry;//商户行业
	private String token;
	private Date loginTime;
	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
}
