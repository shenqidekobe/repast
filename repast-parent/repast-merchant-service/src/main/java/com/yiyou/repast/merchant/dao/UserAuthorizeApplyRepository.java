package com.yiyou.repast.merchant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.merchant.model.UserAuthorizeApply;

public interface UserAuthorizeApplyRepository extends JpaRepository<UserAuthorizeApply, Long> {
	
	//@Query("from UserAuthorizeApply where userId=? order by id desc")
	List<UserAuthorizeApply> findByUserIdOrderByIdDesc(Long userId);
	
	List<UserAuthorizeApply> findAllByMerchantId(Long merchantId);

}
