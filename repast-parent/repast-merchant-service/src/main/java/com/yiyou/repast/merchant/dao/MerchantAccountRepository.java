package com.yiyou.repast.merchant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.merchant.model.MerchantAccount;

public interface MerchantAccountRepository extends JpaRepository<MerchantAccount, Long> {
	
	MerchantAccount findByLoginNameAndPassword(String loginName,String password);

}
