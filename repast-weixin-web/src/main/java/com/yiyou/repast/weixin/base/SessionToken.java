package com.yiyou.repast.weixin.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户注册授权session
 * */
public class SessionToken implements Serializable{
	
	private static final long serialVersionUID = 348051708546226611L;
	private Long userId;
	private String userName;
	private String phone;
	private Long merchantId;
	private String openId;
	private String token;
	private String deskNum;
	private Date createTime;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDeskNum() {
		return deskNum;
	}
	public void setDeskNum(String deskNum) {
		this.deskNum = deskNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
