package com.yiyou.repast.platform.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 平台角色菜单关系
 */
@Entity
@Table(name = "t_sys_group_access")
public class GroupAccess implements java.io.Serializable {

	private static final long serialVersionUID = 6597682676205965765L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer groupId;
	private Integer catalogId;

	public GroupAccess() {
	}

	public GroupAccess(Integer groupId, Integer catalogId) {
		this.groupId = groupId;
		this.catalogId = catalogId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

}