package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import repast.yiyou.common.base.EnumDefinition.AuthorizeAuditStaus;

/**
 * 用户申请授权
 */
@Entity
@Table(name = "t_user_authorize_apply")
public class UserAuthorizeApply implements Serializable{

	private static final long serialVersionUID = -4825044551137077780L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;
	private Long userId;//申请人ID
	private String userName;//申请人名称
	
	private Integer count;//人数
	private BigDecimal amount;//套餐价格
	private String company;//公司
	private String dept;//部门
	private String repastTime;//就餐日期
	private String reason;//理由
	private Date createTime;//申请时间
	
	@Enumerated(EnumType.STRING)
	private AuthorizeAuditStaus auditStatus;//审核状态
	private Long auditId;//审核人ID
	private String auditName;//审核人姓名
	private String auditReason;//审核理由
	private Date auditTime;//审核时间
	
	
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRepastTime() {
		return repastTime;
	}
	public void setRepastTime(String repastTime) {
		this.repastTime = repastTime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public AuthorizeAuditStaus getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(AuthorizeAuditStaus auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditReason() {
		return auditReason;
	}
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Long getAuditId() {
		return auditId;
	}
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	
}
