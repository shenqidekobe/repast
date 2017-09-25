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

import repast.yiyou.common.base.EnumDefinition.UserWhiteStaus;
import repast.yiyou.common.base.EnumDefinition.UserWhiteType;

/**
 * 用户白名单对象
 */
@Entity
@Table(name = "t_user_white")
public class UserWhite implements Serializable{

	private static final long serialVersionUID = 119430789917117893L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;
	
	private String company;//公司名
	private String dept;//部门名
	private String phone;//手机号码
	private String userName;//用户名
	private String settleWay;//结算方式
	
	@Enumerated(EnumType.STRING)
	private UserWhiteType type;//类型
	@Enumerated(EnumType.STRING)
	private UserWhiteStaus status;//状态
	
	private BigDecimal chargeAamount;//充值金额
	private Byte permission;//是否拥有签单权限
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id")
	private UserGrade grade;//用户等级
	
	private String remark;//备注
	private Date createTime;//创建时间
	
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSettleWay() {
		return settleWay;
	}
	public void setSettleWay(String settleWay) {
		this.settleWay = settleWay;
	}
	public UserWhiteStaus getStatus() {
		return status;
	}
	public void setStatus(UserWhiteStaus status) {
		this.status = status;
	}
	public UserGrade getGrade() {
		return grade;
	}
	public void setGrade(UserGrade grade) {
		this.grade = grade;
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
	public UserWhiteType getType() {
		return type;
	}
	public void setType(UserWhiteType type) {
		this.type = type;
	}
	public BigDecimal getChargeAamount() {
		return chargeAamount;
	}
	public void setChargeAamount(BigDecimal chargeAamount) {
		this.chargeAamount = chargeAamount;
	}
	public Byte getPermission() {
		return permission;
	}
	public void setPermission(Byte permission) {
		this.permission = permission;
	}
}
