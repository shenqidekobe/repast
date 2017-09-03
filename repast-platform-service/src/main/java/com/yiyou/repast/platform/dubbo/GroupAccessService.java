package com.yiyou.repast.platform.dubbo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.platform.dao.GroupAccessRepository;
import com.yiyou.repast.platform.model.GroupAccess;
import com.yiyou.repast.platform.service.IGroupAccessService;

@Service
public class GroupAccessService implements IGroupAccessService{
	
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
	public Page<GroupAccess> findGroupAccessList(Integer groupId, int page,int size) {
		return null;
	}

	@Override
	public Page<GroupAccess> findGroupAccessListByGroupIds(String groupIds, int page,int size) {
		return null;
	}

	@Override
	public List<GroupAccess> findGroupAccessByCatalogId(Integer catalogId) {
		GroupAccess group=new GroupAccess();
		group.setCatalogId(catalogId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<GroupAccess> example = Example.of(group, matcher); 
	    List<GroupAccess> list=groupAccessRepository.findAll(example);
		return list;
	}

}
