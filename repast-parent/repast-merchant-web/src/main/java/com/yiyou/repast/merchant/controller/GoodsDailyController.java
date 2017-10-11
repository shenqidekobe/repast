package com.yiyou.repast.merchant.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.base.ThreadContextHolder;
import com.yiyou.repast.merchant.model.DailyGoods;
import com.yiyou.repast.merchant.service.IDailyGoodsService;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import com.yiyou.repast.merchant.service.IGoodsService;

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
        model.addAttribute("categoryList", goodsCategoryService.findAll(ThreadContextHolder.getCurrentMerchantId()));
        return "/goodsDaily/list";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<DailyGoods> listData(Integer page, Integer pageSize, Long date) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        return dailyGoodsService.findByDate(ThreadContextHolder.getCurrentMerchantId(), sdf.format(new Date(date)));
    }

    @PostMapping("/edit")
    @ResponseBody
    public RspResult edit(Long today, @RequestParam(value = "goodsIds[]", required = false) List<Long> goodsIds, Model model) {
        try {
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            dailyGoodsService.editByDate(ThreadContextHolder.getCurrentMerchantId(),sdf.format( new Date(today)),goodsIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new RspResult(505, "");
        }
        return new RspResult();
    }
}
