package com.yiyou.repast.merchant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.merchant.model.MerchantMenu;

public interface MerchantMenuRepository extends JpaRepository<MerchantMenu, Long> {
	
	List<MerchantMenu> findOrderBySortAsc(Long merchantId);

}
