package com.yiyou.repast.merchant.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 角色菜单关系
 */
@Entity
@Table(name = "t_merchant_role_menu")
public class MerchantRoleMenu implements Serializable {

	private static final long serialVersionUID = -958397003919519754L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "roleId", insertable = false, updatable = false)
	private MerchantRole role;

	@ManyToOne
	@JoinColumn(name = "menuId", insertable = false, updatable = false)
	private MerchantMenu menu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MerchantRole getRole() {
		return role;
	}

	public void setRole(MerchantRole role) {
		this.role = role;
	}

	public MerchantMenu getMenu() {
		return menu;
	}

	public void setMenu(MerchantMenu menu) {
		this.menu = menu;
	}

}
