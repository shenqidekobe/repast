package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.DailyGoods;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.service.IDailyGoodsService;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import com.yiyou.repast.merchant.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repast.yiyou.common.util.DataGrid;

import java.util.ArrayList;
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

    @GetMapping("/remove")
    public String delete(Long id, Model model) {
        dailyGoodsService.remove(Constants.MERCHANT_ID, id);
        return "redirect:/daily";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<DailyGoods> listData(Integer page, Integer pageSize, String date) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        DataGrid<DailyGoods> data = dailyGoodsService.findList(Constants.MERCHANT_ID, page, pageSize);
        return dailyGoodsService.findByDate(Constants.MERCHANT_ID, date);
    }

    @PostMapping("/edit")
    @ResponseBody
    public RspResult edit(String today, @RequestParam(value = "goodsIds[]", required = false) List<Long> goodsIds, Model model) {
        try {
            dailyGoodsService.deleteByDate(Constants.MERCHANT_ID, today);
            List<Goods> byIds = goodsService.findByIds(Constants.MERCHANT_ID, goodsIds);
            List<DailyGoods> list = new ArrayList<>();
            for (Goods goods : byIds) {
                DailyGoods dailyGoods = new DailyGoods();
                dailyGoods.setAmount(goods.getAmount());
                dailyGoods.setGoods(goods);
                list.add(dailyGoods);
            }
            dailyGoodsService.save(Constants.MERCHANT_ID, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new RspResult(505, "");
        }
        return new RspResult();
    }
}
