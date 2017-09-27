package com.yiyou.repast.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.base.EnumDefinition.PayWay;

/**
 * 订单表
 */
@Entity
@Table(name = "t_order")
public class Order implements Serializable{

	private static final long serialVersionUID = 4704542642694999182L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;//商户ID
	private Long userId;//用户ID
	private String userName;//用户名
	
	private String orderId;//订单号
	private String deskNum;//桌号
	private Integer peopleCount;//用餐人数
	
	@Enumerated(EnumType.STRING)
	private PayWay payWay;//支付方式
	@Enumerated(EnumType.STRING)
	private OrderStaus status;//状态
	
	private BigDecimal amount;//订单金额
	private BigDecimal discountAmount;//优惠金额
	
	private Date createTime;//下单时间
	private Date settleTime;//结算时间
	private Long createUser;//下单用户
	private String predictDate;//预计就餐日期
	private String predictTime;//预计就餐时间
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
	@OrderBy("id asc")
	private Set<OrderItem> items = new HashSet<>();
	
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
	public String getOrderId() {
		return orderId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDeskNum() {
		return deskNum;
	}
	public void setDeskNum(String deskNum) {
		this.deskNum = deskNum;
	}
	public Integer getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	public Set<OrderItem> getItems() {
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	public PayWay getPayWay() {
		return payWay;
	}
	public void setPayWay(PayWay payWay) {
		this.payWay = payWay;
	}
	public OrderStaus getStatus() {
		return status;
	}
	public void setStatus(OrderStaus status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public String getPredictDate() {
		return predictDate;
	}
	public void setPredictDate(String predictDate) {
		this.predictDate = predictDate;
	}
	public String getPredictTime() {
		return predictTime;
	}
	public void setPredictTime(String predictTime) {
		this.predictTime = predictTime;
	}
	public Date getSettleTime() {
		return settleTime;
	}
	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getPredictDateStr() {
		if(predictDate==null||predictDate.equals(""))return "今天中午12点";
		if(predictDate.equals("today"))return "今天中午12点";
		if(predictDate.equals("tomorrow"))return "名天中午12点";
		return "今天中午12点";
	}
	public String getStatusName(){
		return status.getName();
	}
}
