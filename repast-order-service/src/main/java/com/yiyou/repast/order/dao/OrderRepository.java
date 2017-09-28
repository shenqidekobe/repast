package com.yiyou.repast.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yiyou.repast.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>,JpaSpecificationExecutor<Order>{

}
