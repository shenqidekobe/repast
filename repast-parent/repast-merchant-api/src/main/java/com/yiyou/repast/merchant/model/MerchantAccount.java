package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import repast.yiyou.common.base.EnumDefinition.AccountStaus;
import repast.yiyou.common.base.EnumDefinition.AccountType;

/**
 *商家后端账户表
 * */
@Entity
@Table(name = "t_merchant_account")
public class MerchantAccount implements Serializable{

	private static final long serialVersionUID = -8670934997374209289L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
	private MerchantRole role;
	
	private String loginName;
	private String userName;
	private String password;
	@Enumerated(EnumType.STRING)
	private AccountType type;
	@Enumerated(EnumType.STRING)
	private AccountStaus status;
	private String remark;
	private Date createTime;
	private Date loginTime;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public MerchantRole getRole() {
		return role;
	}
	public void setRole(MerchantRole role) {
		this.role = role;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public AccountStaus getStatus() {
		return status;
	}
	public void setStatus(AccountStaus status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getTypeName(){
		return type==null?"":type.getName();
	}
	public String getStatusName(){
		return status==null?"":status.getName();
	}
}
