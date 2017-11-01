package com.yiyou.repast.platform.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repast.yiyou.common.base.EnumDefinition;
import repast.yiyou.common.util.DataGrid;

@Controller
@RequestMapping("order")
public class OrderController {
    /**
     * 由repast-order-service提供
     */
    @Reference
    private IOrderService orderService;

    @RequestMapping()
    public String index() {
        return "admin/merchant/order";
    }

    /**
     * 订单列表
     */
    @RequestMapping(value = "listData", method = RequestMethod.POST)
    public String userList(Model model, Long merchantId, EnumDefinition.OrderStaus status, String startTime, String endTime, Integer page, Integer pageSize) {
        page = page == null ? page = 0 : page - 1;
        pageSize = pageSize == null ? page = 10 : pageSize;
        DataGrid<Order> groupPage = orderService.findOrderList(merchantId,"","",status,startTime,endTime,page, pageSize);
        model.addAttribute("dataPage", groupPage);
        return "admin/merchant/order_frag";
    }

}
