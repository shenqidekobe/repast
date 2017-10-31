package com.yiyou.repast.order.model;

import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.base.EnumDefinition.PayWay;
import repast.yiyou.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

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
	private String userName;//用户名，
	
	private String orderId;//订单号
	private String deskNum;//桌号
	private Integer peopleCount;//用餐人数
	private String predictDate;//预计就餐日期
	private String predictTime;//预计就餐时间
	
	private String address;//收货地址
	private String phone;//收货人电话
	private String realName;//收货人姓名
	
	@Enumerated(EnumType.STRING)
	private PayWay payWay;//支付方式
	@Enumerated(EnumType.STRING)
	private OrderStaus status;//状态
	
	private BigDecimal amount;//订单金额
	private BigDecimal discountAmount;//优惠金额
	private BigDecimal cancelAmount;//取消的金额
	
	private Date createTime;//下单时间
	private Date settleTime;//结算时间
	private Date cancelTime;//取消时间
	
	private Long createUser;//下单用户
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order",cascade=CascadeType.REMOVE)
	@OrderBy("id desc ")
	private Set<OrderItem> items = new TreeSet<>();
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public Date getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
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
	public BigDecimal getCancelAmount() {
		return cancelAmount;
	}
	public void setCancelAmount(BigDecimal cancelAmount) {
		this.cancelAmount = cancelAmount;
	}
	public String getPredictDateStr() {
		if(predictDate==null||predictDate.equals(""))return "今天中午12点";
		if(predictDate.equals("today"))return "今天中午12点";
		if(predictDate.equals("tomorrow"))return "明天中午12点";
		return "今天中午12点";
	}
	public String getStatusName(){
		if(status==null)return "";
		return status.getName();
	}
	public String getCreateTimeStr() {
		return CommonUtils.format(createTime, "MM-dd HH:mm");
	}
	public String getCancelTimeStr() {
		return CommonUtils.format(cancelTime, "MM-dd HH:mm");
	}
	public String getSettleTimeStr() {
		return CommonUtils.format(settleTime, "MM-dd HH:mm");
	}
}
