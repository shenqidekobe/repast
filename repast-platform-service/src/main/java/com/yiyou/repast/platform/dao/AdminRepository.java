package com.yiyou.repast.platform.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.platform.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	Admin findByLoginNameAndPassword(String loginName, String password);
	
}
