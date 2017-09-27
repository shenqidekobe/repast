package com.yiyou.repast.merchant.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品辅料
 */
@Entity
@Table(name = "t_goods_aux")
public class GoodsAux implements Serializable {

	private static final long serialVersionUID = 1801573277087560343L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_id")
	private Goods goods;//商品
	
	private String name;//辅料名称
	private Byte newon;//是否新品
	private String remark;//描述
	private BigDecimal amount;//价格
	private Integer sales=0;//累计销量
	private Date createTime;
	private String operUser;

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
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getNewon() {
		return newon;
	}
	public void setNewon(Byte newon) {
		this.newon = newon;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOperUser() {
		return operUser;
	}
	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}
	
}
