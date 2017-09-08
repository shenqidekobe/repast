package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private User user;
	
	private Date repastTime;//就餐日期
	private Integer count;//人数
	private BigDecimal amount;//套餐价格
	private String reason;//理由
	private Date createTime;//申请时间
	@Enumerated(EnumType.STRING)
	private AuthorizeAuditStaus auditStatus;//审核状态
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getRepastTime() {
		return repastTime;
	}
	public void setRepastTime(Date repastTime) {
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
	
}
