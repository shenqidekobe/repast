package com.yiyou.repast.merchant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.merchant.model.Sms;

public interface SmsRepository extends JpaRepository<Sms,Long> {
	
	Sms findSmsByMobileLikeContent(String mobile,String content);

}
