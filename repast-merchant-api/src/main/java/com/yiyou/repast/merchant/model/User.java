package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户对象
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable{

	private static final long serialVersionUID = 119430789917117893L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String openId;//微信ID
	private String avatar;//头像
	private String nickName;//用户名
	private String phone;//手机号码

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id")
	private UserGrade grade;//用户等级
	
	private Date createTime;
	private Date loginTime;
	
	@OneToMany(mappedBy = "merchant",fetch=FetchType.EAGER)
	private Set<UserMerchant> merchants = new HashSet<>();
	
	@Transient
	private Long merchantId;//当前的商户ID
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public UserGrade getGrade() {
		return grade;
	}
	public void setGrade(UserGrade grade) {
		this.grade = grade;
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
	public Set<UserMerchant> getMerchants() {
		return merchants;
	}
	public void setMerchants(Set<UserMerchant> merchants) {
		this.merchants = merchants;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
}
