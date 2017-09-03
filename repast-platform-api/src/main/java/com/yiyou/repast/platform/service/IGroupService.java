package com.yiyou.repast.platform.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.yiyou.repast.platform.model.Group;

public interface IGroupService {
	
	Group save(Group obj);
	
	Group update(Group obj);
	
	Group getById(Integer id);

	int removeById(Integer id);
	
	boolean validateName(String name);

	List<Group> getGroupList();
	
	Page<Group> findGroupList(String name, int page,int size);
	

}
