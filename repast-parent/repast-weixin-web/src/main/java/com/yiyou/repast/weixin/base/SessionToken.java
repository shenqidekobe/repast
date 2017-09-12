package com.yiyou.repast.weixin.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户注册授权session
 * */
public class SessionToken implements Serializable{
	
	private static final long serialVersionUID = 348051708546226611L;
	private Long userId;
	private String openId;
	private String token;
	private Date createTime;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
