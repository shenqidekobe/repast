package com.yiyou.repast.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.order.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
