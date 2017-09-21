package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.model.DailyGoods;
import com.yiyou.repast.merchant.service.IDailyGoodsService;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import repast.yiyou.common.util.DataGrid;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 每日商品控制器
 */
@Controller
@RequestMapping("/daily")
public class GoodsDailyController {
    @Reference
    private IDailyGoodsService dailyGoodsService;

    @Reference
    private IGoodsCategoryService goodsCategoryService;

    @GetMapping()
    public String dailyManager(Model model) {
        model.addAttribute("categoryList", goodsCategoryService.findAll(Constants.MERCHANT_ID));
        return "/goodsDaily/list";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<DailyGoods> listData(Integer page, Integer pageSize, Long date) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        DataGrid<DailyGoods> data = dailyGoodsService.findList(Constants.MERCHANT_ID, page, pageSize);
        // TODO: 2017/9/12 分页
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return dailyGoodsService.findByDate(Constants.MERCHANT_ID, format);
    }

    @GetMapping("/edit")
    public String edit(Long id, Model model) {

        if (id == null) {
            return "/goods/add";
        }
        model.addAttribute("obj", dailyGoodsService.findById(Constants.MERCHANT_ID, id));
        return "/goods/edit";
    }

}
