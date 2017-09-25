package com.yiyou.repast.merchant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yiyou.repast.merchant.model.MerchantRoleMenu;

public interface MerchantRoleMenuRepository extends JpaRepository<MerchantRoleMenu, Long> {

    @Modifying
    @Transactional
    @Query("delete from MerchantRoleMenu where role.id = ?1")
    void deleteByRoleId(Long roleId);
}

