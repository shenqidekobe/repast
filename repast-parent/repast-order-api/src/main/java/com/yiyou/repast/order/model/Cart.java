package com.yiyou.repast.order.model;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 购物车对象
 */
@Entity
@Table(name = "t_cart")
public class Cart implements Serializable{

	private static final long serialVersionUID = -3530652719574583251L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;//用户ID
	
    private String deskNum;//桌号
	private BigDecimal amount;//总金额
	private Date createTime;//创建时间
	
	@ManyToMany(mappedBy = "cart", targetEntity = CartItem.class, fetch = FetchType.EAGER)
	private Set<CartItem> items = new HashSet<>();
	
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
	public String getDeskNum() {
		return deskNum;
	}
	public void setDeskNum(String deskNum) {
		this.deskNum = deskNum;
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
	public Set<CartItem> getItems() {
		return items;
	}
	public void setItems(Set<CartItem> items) {
		this.items = items;
	}
	
}
