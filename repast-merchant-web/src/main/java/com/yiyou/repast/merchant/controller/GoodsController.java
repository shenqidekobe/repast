package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.model.GoodsCategory;
import com.yiyou.repast.merchant.service.IGoodsAuxService;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.merchant.service.IGoodsSpecService;
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

    @Reference
    private IGoodsCategoryService goodsCategoryService;

    @Reference
    private IGoodsSpecService goodsSpecService;

    @Reference
    private IGoodsAuxService goodsAuxService;

    @GetMapping()
    public String goodsManager(Model model) {
        return "/goods/list";
    }


    @GetMapping("/edit")
    public String edit(Long id, Model model) {
        model.addAttribute("parentList", this.goodsCategoryService.findAll(Constants.MERCHANT_ID));
        if (id == null) {
            model.addAttribute("specList", this.goodsSpecService.findAll(Constants.MERCHANT_ID));
            model.addAttribute("auxList", this.goodsAuxService.findAll(Constants.MERCHANT_ID));
            return "/goods/add";
        }
        model.addAttribute("obj", this.goodsService.find(Constants.MERCHANT_ID,id));
        return "/goods/edit";
    }




    @ResponseBody
    @PostMapping("/listData.do")
    public List<Goods> listData(Integer page, Integer pageSize) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        DataGrid<Goods> data = goodsService.findList(Constants.MERCHANT_ID, page, pageSize);
        // TODO: 2017/9/12 分页
        return goodsService.findAll(Constants.MERCHANT_ID);
    }



    @ResponseBody
    @PostMapping("/save.do")
    public RspResult save(Goods obj, Long parentId, Long id,Long[] specIds,Long[] auxIds) {
        if (obj == null) {
            return new RspResult(505, "参数错误");
        }
        if (obj.getId() == null) {
            //新增
            GoodsCategory category = goodsCategoryService.find(Constants.MERCHANT_ID, parentId);
            obj.setCategory(category);
            goodsService.save(Constants.MERCHANT_ID, obj);
        } else {
            //保存
        }
        return new RspResult();
    }


}
