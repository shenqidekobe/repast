package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import repast.yiyou.common.util.DataGrid;

import java.util.List;

/**
 * 菜品控制器
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Reference
    private IGoodsService goodsService;



    @GetMapping()
    public String goodsManager(Model model) {
        return "/goods/list";
    }


    @ResponseBody
    @PostMapping("listData.do")
    public List<Goods> listData(Integer page, Integer pageSize) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        DataGrid<Goods> data = goodsService.findList(Constants.MERCHANT_ID, page, pageSize);
        return data.getRecords();
    }



}
