package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.service.IRecommendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
    @Reference
    private IRecommendService recommendService;

    @GetMapping()
    public String recommendManager(Model model) {
        return "/goodsRecommend/list";
    }
}
