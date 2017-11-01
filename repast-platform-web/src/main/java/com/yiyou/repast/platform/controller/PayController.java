package com.yiyou.repast.platform.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.pay.model.PaymentRecord;
import com.yiyou.repast.pay.service.IPaymentRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repast.yiyou.common.util.DataGrid;

@Controller
@RequestMapping("pay")
public class PayController {
    /**
     * 由repast-pay-service提供
     */
    @Reference
    private IPaymentRecordService recordService;

    @RequestMapping()
    public String index() {
        return "admin/merchant/pay";
    }

    /**
     * 订单列表
     */
    @PostMapping("listData")
    public String userList(Model model, Long merchantId, Integer page, Integer pageSize) {
        page = page == null ? page = 0 : page - 1;
        pageSize = pageSize == null ? page = 10 : pageSize;
        DataGrid<PaymentRecord> groupPage = recordService.findList(merchantId,page, pageSize);
        model.addAttribute("dataPage", groupPage);
        return "admin/merchant/order_frag";
    }
}
