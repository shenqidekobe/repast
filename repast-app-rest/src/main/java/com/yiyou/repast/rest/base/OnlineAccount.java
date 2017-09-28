package com.yiyou.repast.rest.base;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 在线终端用户
 * */
public class OnlineAccount {
	
	private static Map<Long,OnlineAccount> onlineMap=new ConcurrentHashMap<>();
	
	private static Map<Long,OnlineAccount> cacheMap=new ConcurrentHashMap<>();//永久存储
	
	private Long accountId;
	private String account;
	private Long merchantId;
	private Date createTime;
	private Date refershTime;
	
	
	public OnlineAccount() {}
	public OnlineAccount(Long accountId, String account, Long merchantId) {
		super();
		this.accountId = accountId;
		this.account = account;
		this.merchantId = merchantId;
		this.createTime = new Date();
		this.refershTime=new Date();
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getRefershTime() {
		return refershTime;
	}
	public void setRefershTime(Date refershTime) {
		this.refershTime = refershTime;
	}
	
	public static void register(Long accountId,Long merchantId,String account) {
		if(onlineMap.containsKey(accountId)) {
			refersh(accountId);
			return;
		}
		OnlineAccount obj=new OnlineAccount(accountId, account, merchantId);
		onlineMap.put(accountId, obj);
		
		cacheMap.put(accountId, obj);
	}
	
	public static void refersh(Long accountId) {
		OnlineAccount obj=onlineMap.get(accountId);
		if(obj!=null) {
			obj.setRefershTime(new Date());
			onlineMap.put(accountId, obj);
		}else {
			OnlineAccount cache=cacheMap.get(accountId);
			if(cache!=null) {
				cache.setRefershTime(new Date());
				onlineMap.put(accountId, cache);
			}
		}
	}
	
	public static void refershAll() {
		for(Long accountId:onlineMap.keySet()) {
			OnlineAccount obj=onlineMap.get(accountId);
			if(obj!=null) {
				obj.setRefershTime(new Date());
				onlineMap.put(accountId, obj);
			}
		}
	}
	
	public static List<OnlineAccount> getList(){
		return onlineMap.values().stream().collect(Collectors.toList());
	}
	
	public static void clearExpire() {
		for (Long accountId:onlineMap.keySet()) {
			OnlineAccount obj=onlineMap.get(accountId);
			boolean flag = false;
			if (obj != null) {
				Long time = obj.getRefershTime().getTime();
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MINUTE, -40);//多少时间内没操作则移除掉
				Long ct = calendar.getTime().getTime();
				if (ct <= time) {
					flag = true;
				}
				if(!flag) {
					onlineMap.remove(accountId);
				}
			}
		}
	}

}
