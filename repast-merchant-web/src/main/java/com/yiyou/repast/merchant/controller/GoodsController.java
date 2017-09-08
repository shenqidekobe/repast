package com.yiyou.repast.merchant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜品控制器
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
//    @Reference
//    private IMerchantGoodsService merchantGoodsService;
//    @Reference
//    private IMerchantGoodsCategoryService merchantGoodsCategoryService;

    @GetMapping("/manager")
    public String goodsManager(Model model) {
        return "/goods/list";
    }

    @GetMapping("/catagory")
    public String catagory(Model model) {
        return "/goodsCategory/list";
    }

}
