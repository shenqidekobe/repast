package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商户桌子
 */
@Entity
@Table(name = "t_merchant_desk")
public class MerchantDesk implements Serializable{

	private static final long serialVersionUID = 4435376678780490498L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;
	private String deskNum;//桌子号
	private Integer seatCount;//座位数量
	private String remark;//说明
	private Date createTime;
	
	
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
	public String getDeskNum() {
		return deskNum;
	}
	public void setDeskNum(String deskNum) {
		this.deskNum = deskNum;
	}
	public Integer getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
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
	
}
