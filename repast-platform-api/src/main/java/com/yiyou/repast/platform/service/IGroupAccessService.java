package com.yiyou.repast.platform.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.yiyou.repast.platform.model.GroupAccess;

import repast.yiyou.common.util.DataGrid;

public interface IGroupAccessService {
	
	GroupAccess save(GroupAccess obj);
	
	GroupAccess remove(GroupAccess obj);

	DataGrid<GroupAccess> findGroupAccessList(Integer groupId, int page,int size);

	Page<GroupAccess> findGroupAccessListByGroupIds(String groupIds, int page,int size);
	
	List<GroupAccess> findGroupAccessByCatalogId(Integer catalogId);
	
}
