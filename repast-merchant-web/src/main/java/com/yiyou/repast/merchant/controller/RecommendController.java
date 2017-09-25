package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.RecommendGoods;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.merchant.service.IRecommendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
    @Reference
    private IRecommendService recommendService;
    @Reference
    private IGoodsService goodsService;
    @Reference
    private IGoodsCategoryService goodsCategoryService;

    @GetMapping()
    public String recommendManager(Model model) {
        model.addAttribute("categoryList", goodsCategoryService.findAll(Constants.MERCHANT_ID));
        return "/goodsRecommend/list";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<RecommendGoods> listData(Integer page, Integer pageSize, String date) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        return recommendService.findAll(Constants.MERCHANT_ID);
    }

    @PostMapping("/edit")
    @ResponseBody
    public RspResult edit(@RequestParam(value = "goodsIds[]", required = false) List<Long> goodsIds, Model model){
        try {
            recommendService.upDate(Constants.MERCHANT_ID,goodsIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new RspResult(505, "");
        }
        return new RspResult();
    }

}
