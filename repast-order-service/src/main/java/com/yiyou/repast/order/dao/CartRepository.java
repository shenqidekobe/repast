package com.yiyou.repast.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.order.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
