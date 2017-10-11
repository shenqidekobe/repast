package com.yiyou.repast.merchant.service;

import java.util.List;

import com.yiyou.repast.merchant.model.UserAddress;

/**
 * 每日菜单
 */
public interface IUserAddressService {

    List<UserAddress> findByUserId(Long userId);
    
    UserAddress save(UserAddress obj);
    
    UserAddress update(UserAddress obj);
    
    UserAddress getById(Long id);
    
    void remove(Long id);

}
