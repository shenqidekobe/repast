package com.yiyou.repast.merchant.base;

import java.io.Serializable;

/**
 * mvc response body result
 * */
public class RspResult implements Serializable{
	
	private static final long serialVersionUID = -3721040338062968656L;
	
	private Integer status=200;
	private String message;
	private Object data;
	
	public RspResult() {}
	public RspResult(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
