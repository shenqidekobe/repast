package com.yiyou.repast.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiyou.repast.order.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
