package com.yiyou.repast.platform.service;

import com.yiyou.repast.order.model.Order;
import repast.yiyou.common.util.DataGrid;

/**
 * 订单列表
 */
public interface IOrderListService {
    /**
     * 商户订单列表
     *
     * @param merhantName 商户名
     * @param page
     * @param size
     * @return
     */
    DataGrid<Order> findList(String merhantName, int page, int size);
}
