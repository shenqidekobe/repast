package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.DailyGoods;
import com.yiyou.repast.merchant.service.IDailyGoodsService;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import com.yiyou.repast.merchant.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 每日商品控制器
 */
@Controller
@RequestMapping("/daily")
public class GoodsDailyController {
    @Reference
    private IGoodsService goodsService;
    @Reference
    private IGoodsCategoryService goodsCategoryService;
    @Reference
    private IDailyGoodsService dailyGoodsService;

    @GetMapping()
    public String dailyManager(Model model) {
        model.addAttribute("categoryList", goodsCategoryService.findAll(Constants.MERCHANT_ID));
        return "/goodsDaily/list";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<DailyGoods> listData(Integer page, Integer pageSize, String date) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        return dailyGoodsService.findByDate(Constants.MERCHANT_ID, date);
    }

    @PostMapping("/edit")
    @ResponseBody
    public RspResult edit(String today, @RequestParam(value = "goodsIds[]", required = false) List<Long> goodsIds, Model model) {
        try {
            dailyGoodsService.editByDate(Constants.MERCHANT_ID, today,goodsIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new RspResult(505, "");
        }
        return new RspResult();
    }
}
