package com.yiyou.repast.order.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import repast.yiyou.common.base.EnumDefinition.OrderProcessStatus;

/**
 * 订单处理记录
 * */
@Entity
@Table(name = "t_order_process")
public class OrderProcess implements Serializable {

	private static final long serialVersionUID = 5008323647517114435L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;//商户ID
	private Long orderId;//订单ID
	private Long accountId;//处理账户ID
	@Enumerated(EnumType.STRING)
	private OrderProcessStatus status;//处理状态
	private Date createTime;//创建时间
	private Date pushTime;//推送时间
	private Date processTime;//处理时间
	
	
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
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public OrderProcessStatus getStatus() {
		return status;
	}
	public void setStatus(OrderProcessStatus status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getProcessTime() {
		return processTime;
	}
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
	public Date getPushTime() {
		return pushTime;
	}
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	
}
