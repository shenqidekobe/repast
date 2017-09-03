package com.yiyou.repast.platform.dubbo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.platform.dao.GroupRepository;
import com.yiyou.repast.platform.model.Group;
import com.yiyou.repast.platform.service.IGroupService;

@Service
public class GroupService implements IGroupService{
	
	@Resource
	private GroupRepository groupRepository;

	@Override
	public Group save(Group obj) {
		return groupRepository.save(obj);
	}

	@Override
	public Group update(Group obj) {
		return groupRepository.save(obj);
	}

	@Override
	public Group getById(Integer id) {
		return groupRepository.findOne(id);
	}

	@Override
	public int removeById(Integer id) {
		groupRepository.delete(id);
		return 0;
	}

	@Override
	public boolean validateName(String name) {
		Group group=new Group();
		group.setName(name);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Group> example = Example.of(group, matcher); 
	    List<Group> list=groupRepository.findAll(example);
		return list.isEmpty();
	}

	@Override
	public List<Group> getGroupList() {
		return null;
	}

	@Override
	public Page<Group> findGroupList(String name,int page,int size) {
		return null;
	}

}
