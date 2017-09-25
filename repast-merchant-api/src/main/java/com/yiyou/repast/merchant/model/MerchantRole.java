package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 商家后端角色
 */
@Entity
@Table(name = "t_merchant_role")
public class MerchantRole implements Serializable {

	private static final long serialVersionUID = -8670934997374209289L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;

	@OneToMany(mappedBy = "role",fetch=FetchType.EAGER)
	private Set<MerchantRoleMenu> roleMenu = new HashSet<>();

	private String name;
	private String remark;
	private Date createTime;

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
	public Set<MerchantRoleMenu> getRoleMenu() {
		return roleMenu;
	}

	public void setRoleMenu(Set<MerchantRoleMenu> roleMenu) {
		this.roleMenu = roleMenu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
