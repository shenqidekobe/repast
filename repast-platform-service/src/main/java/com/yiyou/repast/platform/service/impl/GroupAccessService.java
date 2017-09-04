package com.yiyou.repast.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.platform.dao.GroupAccessRepository;
import com.yiyou.repast.platform.model.GroupAccess;
import com.yiyou.repast.platform.service.IGroupAccessService;
import com.yiyou.repast.platform.tools.PageConvertDataGrid;

import repast.yiyou.common.util.DataGrid;

@Service
public class GroupAccessService implements IGroupAccessService {

	@Resource
	private GroupAccessRepository groupAccessRepository;

	@Override
	public GroupAccess save(GroupAccess obj) {
		return groupAccessRepository.save(obj);
	}

	@Override
	public GroupAccess remove(GroupAccess obj) {
		groupAccessRepository.delete(obj);
		return obj;
	}

	@Override
	public DataGrid<GroupAccess> findGroupAccessList(Integer groupId, int page, int size) {
		GroupAccess ga = new GroupAccess();
		if (groupId != null)
			ga.setGroupId(groupId);
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<GroupAccess> example = Example.of(ga, matcher);
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<GroupAccess> pages = groupAccessRepository.findAll(example, pageable);
		return new PageConvertDataGrid.Bulid<GroupAccess>().page(pages).build().getData();
	}

	@Override
	public DataGrid<GroupAccess> findGroupAccessListByGroupIds(String groupIds, int page, int size) {
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		String[] pps = groupIds.split(",");
		List<Integer> ids = new ArrayList<>();
		for (String id : pps)
			ids.add(Integer.parseInt(id));
		Page<GroupAccess> pages = groupAccessRepository.findGroupAccessListByGroupIds(ids, pageable);
		return new PageConvertDataGrid.Bulid<GroupAccess>().page(pages).build().getData();
	}

	@Override
	public List<GroupAccess> findGroupAccessByCatalogId(Integer catalogId) {
		GroupAccess group = new GroupAccess();
		group.setCatalogId(catalogId);
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<GroupAccess> example = Example.of(group, matcher);
		List<GroupAccess> list = groupAccessRepository.findAll(example);
		return list;
	}

}
