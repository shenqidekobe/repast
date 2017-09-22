package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.User;

public interface IUserService extends IBaseService<User>{
	
	User save(User user);
	
	User update(User user);
	
	User findById(Long merchantId,Long id);
	
	User findByOpenId(String openId);
	

}
