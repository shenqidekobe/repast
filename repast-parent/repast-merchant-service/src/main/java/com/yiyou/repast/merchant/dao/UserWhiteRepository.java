package com.yiyou.repast.merchant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.merchant.model.UserWhite;

public interface UserWhiteRepository extends JpaRepository<UserWhite,Long> {

	List<UserWhite> findAllByMerchantId(Long merchantId);
}
