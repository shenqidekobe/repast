package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 商品
 */
@Entity
@Table(name = "t_goods")
public class Goods implements Serializable{

	private static final long serialVersionUID = -5737781723933462465L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id")
	private Merchant merchant;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
	private GoodsCategory category;//商品分类
	
    @ManyToMany(targetEntity = GoodsSpec.class, fetch = FetchType.EAGER)  
    @JoinTable(name = "t_goods_spec_r", joinColumns = @JoinColumn(name = "spec_id"), inverseJoinColumns = @JoinColumn(name = "goods_id"))  
    private Set<GoodsSpec> specs = new HashSet<>();  
	
	private String name;//商品名称
	private String pic;//商品名称
	private BigDecimal amount;//商品金额
	
	private Byte newon;//是否新品
	private String remark;//描述
	private Integer sales=0;//累计销量
	private Date createTime;//发布时间
	private String operUser;//操作人
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public GoodsCategory getCategory() {
		return category;
	}
	public void setCategory(GoodsCategory category) {
		this.category = category;
	}
	public Set<GoodsSpec> getSpecs() {
		return specs;
	}
	public void setSpecs(Set<GoodsSpec> specs) {
		this.specs = specs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
