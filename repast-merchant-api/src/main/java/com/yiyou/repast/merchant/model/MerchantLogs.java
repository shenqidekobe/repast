package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商户操作日志表
 */
@Entity
@Table(name = "t_merchant_logs")
public class MerchantLogs implements Serializable{

	private static final long serialVersionUID = 4435376678780490498L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Long merchantId;
	private Long operUserId;//操作用户ID
	private String operUserName;//操作用户名
	
	private String ip;
	private String reqUrl;
	private String methodName;
	private String reqParams;
	private String intro;
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public Long getOperUserId() {
		return operUserId;
	}
	public void setOperUserId(Long operUserId) {
		this.operUserId = operUserId;
	}
	public String getOperUserName() {
		return operUserName;
	}
	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReqUrl() {
		return reqUrl;
	}
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getReqParams() {
		return reqParams;
	}
	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
