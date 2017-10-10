package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import repast.yiyou.common.base.EnumDefinition.IndustryType;
import repast.yiyou.common.base.EnumDefinition.MerchantStaus;
import repast.yiyou.common.base.EnumDefinition.MerchantType;
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
	@Enumerated(EnumType.STRING)
	private MerchantType type;//商户类型
	private String address;//商户地址
	private String longitude;//经度
	private String latitude;//纬度
	private String corporation;//法人代表
	private String idcard;//法人身份证号码
	private String wechatId;//微信管理员ID
	private String alipayId;//支付宝管理员ID
	
	@Enumerated(EnumType.STRING)
	private IndustryType industry;//所属行业
	
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
	public MerchantType getType() {
		return type;
	}
	public void setType(MerchantType type) {
		this.type = type;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getWechatId() {
		return wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	public String getAlipayId() {
		return alipayId;
	}
	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	public IndustryType getIndustry() {
		return industry;
	}
	public void setIndustry(IndustryType industry) {
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
