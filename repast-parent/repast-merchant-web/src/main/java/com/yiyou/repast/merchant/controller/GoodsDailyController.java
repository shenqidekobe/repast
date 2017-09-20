package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.service.IDailyGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 每日菜单控制器
 */
@Controller
@RequestMapping("/daily")
public class GoodsDailyController {
    @Reference
    private IDailyGoodsService dailyGoodsService;

    @GetMapping()
    public String dailyManager(Model model) {
        return "/goodsDaily/list";
    }


}
