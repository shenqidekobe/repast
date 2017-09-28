package com.yiyou.repast.merchant.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * session
 * */
public class SessionToken implements Serializable{
	
	private static final long serialVersionUID = -3920364887014300999L;
	private Long accountId;
	private Long merchantId;
	private String loginName;
	private Long roleId;
	private String roleName;
	private String token;
	private Date createTime;
	private List<String> permissions=new ArrayList<>();
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	@Override
	public String toString() {
		return "SessionToken [accountId=" + accountId + ", merchantId=" + merchantId + ", loginName=" + loginName
				+ ", roleId=" + roleId + ", roleName=" + roleName + ", token=" + token + ", createTime=" + createTime
				+ ", permissions=" + permissions + "]";
	}
	
}
