package com.yiyou.repast.platform.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yiyou.repast.platform.model.GroupAccess;

public interface GroupAccessRepository extends JpaRepository<GroupAccess, Integer> {

	@Query("from GroupAccess s where s.groupId in :groupIds")
	Page<GroupAccess> findGroupAccessListByGroupIds(@Param("groupIds")List<Integer> groupIds, Pageable pageable);

}
