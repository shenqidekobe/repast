package com.yiyou.repast.merchant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yiyou.repast.merchant.model.UserAuthorizeApply;

public interface UserAuthorizeApplyRepository extends JpaRepository<UserAuthorizeApply, Long> {
	
	@Query("from UserAuthorizeApply where userId=:userId order by id desc")
	List<UserAuthorizeApply> findByUserId(@Param("userId") Long userId);

}
