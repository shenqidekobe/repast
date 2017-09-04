package com.yiyou.repast.platform.service;

import java.util.List;

import com.yiyou.repast.platform.model.Group;

import repast.yiyou.common.util.DataGrid;

public interface IGroupService {
	
	Group save(Group obj);
	
	Group update(Group obj);
	
	Group getById(Integer id);

	int removeById(Integer id);
	
	boolean validateName(String name);

	List<Group> getGroupList();
	
	DataGrid<Group> findGroupList(String name, int page,int size);
	

}
