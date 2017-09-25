package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.model.RecommendGoods;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.merchant.service.IRecommendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import repast.yiyou.common.util.DataGrid;

import java.util.List;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
    @Reference
    private IRecommendService recommendService;

    @Reference
    private IGoodsService goodsService;

    @GetMapping()
    public String recommendManager(Model model) {
        return "/goodsRecommend/list";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<RecommendGoods> listData(Integer page, Integer pageSize, String date) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        DataGrid<RecommendGoods> data = recommendService.findList(Constants.MERCHANT_ID, page, pageSize);
        return recommendService.findAll(Constants.MERCHANT_ID);
    }





}
