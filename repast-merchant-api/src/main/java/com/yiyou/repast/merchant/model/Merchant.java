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

import repast.yiyou.common.base.EnumDefinition.MerchantStaus;
import repast.yiyou.common.base.EnumDefinition.SettleWay;

/**
 * 商户对象
 */
@Entity
@Table(name = "t_merchant")
public class Merchant implements Serializable {

	private static final long serialVersionUID = 4251237178411815647L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;//商户名
	private String type;//商户类型
	private String address;//商户地址
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "industry_id")
	private Industry industry;//所属行业
	
	@Enumerated(EnumType.STRING)
	private SettleWay settleWay;//结算方式
	@Enumerated(EnumType.STRING)
	private MerchantStaus status;//状态
	private String remark;//备注描述
	
	private Date startTime;//有效开始时间
	private Date endTime;//有效结束时间
	
	private Date createTime;//创建时间
	private Date auditTime;//审核时间
	private String operUser;//操作人
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Industry getIndustry() {
		return industry;
	}
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SettleWay getSettleWay() {
		return settleWay;
	}
	public void setSettleWay(SettleWay settleWay) {
		this.settleWay = settleWay;
	}
	public MerchantStaus getStatus() {
		return status;
	}
	public void setStatus(MerchantStaus status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getOperUser() {
		return operUser;
	}
	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}

}
