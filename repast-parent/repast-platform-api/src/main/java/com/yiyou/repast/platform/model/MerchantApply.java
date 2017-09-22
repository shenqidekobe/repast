package com.yiyou.repast.platform.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商户和应用的对应关系配置
 */
@Entity
@Table(name = "t_merchant_apply")
public class MerchantApply implements Serializable{

	private static final long serialVersionUID = 4435376678780490498L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Long merchantId;
	private String applyPath;//应用路径
	private String applyName;//应用名称
	private String applyLogo;//应用logo
	private String applyDesc;//应用描述信息
	private String applyDomain;//应用域名地址
	private Integer status;// 0无效1有效
	private Date createTime;
	
	public MerchantApply() {}
	public MerchantApply(Long merchantId, String applyPath) {
		super();
		this.merchantId = merchantId;
		this.applyPath = applyPath;
	}
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
	public String getApplyPath() {
		return applyPath;
	}
	public void setApplyPath(String applyPath) {
		this.applyPath = applyPath;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getApplyLogo() {
		return applyLogo;
	}
	public void setApplyLogo(String applyLogo) {
		this.applyLogo = applyLogo;
	}
	public String getApplyDesc() {
		return applyDesc;
	}
	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}
	public String getApplyDomain() {
		return applyDomain;
	}
	public void setApplyDomain(String applyDomain) {
		this.applyDomain = applyDomain;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
