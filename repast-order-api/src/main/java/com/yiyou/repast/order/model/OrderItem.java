package com.yiyou.repast.order.model;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import repast.yiyou.common.base.CommonConstants;
import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.util.CommonUtils;

/**
 * 订单明细
 */
@Entity
@Table(name = "t_order_item")
@JsonIgnoreProperties(value= {"order"})
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 4704542642694999182L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
	private Order order;
	
	private Long goodsId;//商品ID
	private String goodsType;
	private String goodsName;//runyu数据
	private String goodsPic;//商品图片
	private String auxIds;//商品辅料ID字符串
	private String auxNames;//商品辅料字符串
	private Long specId;//规格ID
	private String specName;//规定名
	private Integer count;//数量
	private BigDecimal amount;//订单金额
	private BigDecimal discountAmount;//优惠金额
	@Enumerated(EnumType.STRING)
	private OrderStaus status;//状态
	
	private Date createTime;//下单时间
	private Date serveTime;//上菜时间
	private Date cancelTime;//取消时间
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getAuxIds() {
		return auxIds;
	}
	public void setAuxIds(String auxIds) {
		this.auxIds = auxIds;
	}
	public String getAuxNames() {
		return auxNames;
	}
	public void setAuxNames(String auxNames) {
		this.auxNames = auxNames;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getSpecId() {
		return specId;
	}
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
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
	public Date getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	public Date getServeTime() {
		return serveTime;
	}
	public void setServeTime(Date serveTime) {
		this.serveTime = serveTime;
	}
	public String getGoodsPic() {
		if(goodsPic==null||goodsPic.length()==0) return "default.png";
		return goodsPic;
	}
	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}
	
	public String getPicUrl() {
		return CommonConstants.IMG_DOMAIN+"/common/img/"+getGoodsPic()+"/0/";
	}
	
	public String getCreateTimeStr() {
		return CommonUtils.format(createTime, "MM-dd HH:mm");
	}
	public String getCancelTimeStr() {
		return CommonUtils.format(cancelTime, "MM-dd HH:mm");
	}
	public String getServeTimeStr() {
		return CommonUtils.format(serveTime, "MM-dd HH:mm");
	}
	
	
}
