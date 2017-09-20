package com.yiyou.repast.weixin.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yiyou.repast.order.model.CartItem;

/**
 * 购物车项分类处理
 * */
public class CartItemMap {
	
	private Map<String,Integer> typeMap;//分类统计
	private List<ItemMap> list;
	
	public static class ItemMap{
		private Long userId;
		private String avatar;
		private String userName;
		private Date createTime;
		private List<CartItem> itemList;
		
		public ItemMap() {}
		public ItemMap(Long userId, String avatar, String userName, Date createTime) {
			super();
			this.userId = userId;
			this.avatar = avatar;
			this.userName = userName;
			this.createTime = createTime;
		}
		public ItemMap(Long userId, String avatar, String userName, Date createTime, List<CartItem> itemList) {
			super();
			this.userId = userId;
			this.avatar = avatar;
			this.userName = userName;
			this.createTime = createTime;
			this.itemList = itemList;
		}
		
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getAvatar() {
			return avatar;
		}
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
		public String getUserName() {
			if(userName==null)return "靓仔";
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public List<CartItem> getItemList() {
			return itemList;
		}
		public void setItemList(List<CartItem> itemList) {
			this.itemList = itemList;
		}
	}

	public Map<String, Integer> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, Integer> typeMap) {
		this.typeMap = typeMap;
	}

	public List<ItemMap> getList() {
		return list;
	}

	public void setList(List<ItemMap> list) {
		this.list = list;
	}
	
}
