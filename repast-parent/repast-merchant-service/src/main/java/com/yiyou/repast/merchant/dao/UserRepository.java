package com.yiyou.repast.merchant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.merchant.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByMerchantId(Long merchantId);

}

