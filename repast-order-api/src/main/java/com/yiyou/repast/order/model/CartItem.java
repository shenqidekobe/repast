package com.yiyou.repast.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import repast.yiyou.common.base.CommonConstants;

/**
 * 购物车列表项
 */
@Entity
@Table(name = "t_cart_item")
public class CartItem implements Serializable{

	private static final long serialVersionUID = -3530652719574583251L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String userName;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
	private Cart cart;
	
	private Long goodsId;//商品ID
	private String goodsName;//runyu数据
	private BigDecimal goodsAmount;//商品价格金额
	private String goodsPic;//商品图片
	private String goodsType;//商品类型{用于分类统计}
	private String auxIds;//商品辅料ID字符串
	private Long specId;//规格ID
	private String specName;//规定名
	private Integer count;//数量
	private BigDecimal amount;//总金额
	private Date createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public String getAuxIds() {
		return auxIds;
	}
	public void setAuxIds(String auxIds) {
		this.auxIds = auxIds;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
}
