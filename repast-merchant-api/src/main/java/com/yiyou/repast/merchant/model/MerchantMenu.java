package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 商户后台彩蛋
 */
@Entity
@Table(name = "t_merchant_menu")
public class MerchantMenu implements Serializable {

	private static final long serialVersionUID = 4251237178411815647L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private MerchantMenu parent;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
	private Set<MerchantMenu> children = new HashSet<>(0);

	@ManyToMany(targetEntity = MerchantRole.class, fetch = FetchType.EAGER)
	@JoinTable(name = "t_merchant_role_menu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
	private Set<MerchantRole> roles = new HashSet<>();

	private String name;
	private String url;
	private String permission;
	private Integer sort;
	private Date createTime;

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

	public MerchantMenu getParent() {
		return parent;
	}

	public void setParent(MerchantMenu parent) {
		this.parent = parent;
	}

	public Set<MerchantMenu> getChildren() {
		return children;
	}

	public void setChildren(Set<MerchantMenu> children) {
		this.children = children;
	}
	public Set<MerchantRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<MerchantRole> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
