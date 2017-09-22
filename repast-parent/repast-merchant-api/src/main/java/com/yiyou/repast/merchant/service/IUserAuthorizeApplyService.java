package com.yiyou.repast.merchant.service;

import com.yiyou.repast.merchant.model.UserAuthorizeApply;

public interface IUserAuthorizeApplyService extends IBaseService<UserAuthorizeApply>{
	
	UserAuthorizeApply save(UserAuthorizeApply obj);
	
	UserAuthorizeApply update(UserAuthorizeApply obj);
	
	UserAuthorizeApply findById(Long id);
	
	UserAuthorizeApply findByUserId(Long userId);

}
