package com.yiyou.repast.platform.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.pay.service.IPaymentRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
