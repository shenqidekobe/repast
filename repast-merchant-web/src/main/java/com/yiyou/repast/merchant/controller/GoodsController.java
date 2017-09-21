package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RBeanUtils;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.model.GoodsCategory;
import com.yiyou.repast.merchant.model.GoodsSpec;
import com.yiyou.repast.merchant.service.IGoodsAuxService;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.merchant.service.IGoodsSpecService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repast.yiyou.common.util.DataGrid;

import java.util.List;
import java.util.Set;

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
        model.addAttribute("parentList", goodsCategoryService.findAll(Constants.MERCHANT_ID));
        model.addAttribute("specList", goodsSpecService.findAll(Constants.MERCHANT_ID));
        model.addAttribute("auxList", goodsAuxService.findAll(Constants.MERCHANT_ID));
        if (id == null) {
            return "/goods/add";
        }
        model.addAttribute("obj", goodsService.findById(Constants.MERCHANT_ID, id));
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

    @GetMapping("/remove")
    public String delete(Long id, Model model) {
        goodsService.remove(Constants.MERCHANT_ID, id);
        return "redirect:/goods";
    }

    @ResponseBody
    @PostMapping("/save.do")
    public RspResult save(Goods obj, Long parentId, Long id,
                          @RequestParam(value = "auxs[]", required = false) List<Long> auxIds,
                          @RequestParam(value = "specIds[]", required = false) List<Long> specIds) {
        if (obj == null) {
            return new RspResult(505, "参数错误");
        }
        Set<GoodsSpec> specs = goodsSpecService.findByIds(specIds);
        String auxs = "";
        for (int i = 0; i < auxIds.size(); i++) {
            if (i == 0) {
                auxs += auxIds.get(i);
            } else {
                auxs += "," + auxIds.get(i);
            }
        }
        if (obj.getId() == null) {
            //新增
            GoodsCategory category = goodsCategoryService.findById(Constants.MERCHANT_ID, parentId);
            obj.setCategory(category);
            obj.setSpecs(specs);
            obj.setAuxIds(auxs);
            goodsService.save(Constants.MERCHANT_ID, obj);
        } else {
            //保存
            Goods pojo = this.goodsService.findById(Constants.MERCHANT_ID, id);
            RBeanUtils.copyProperties(obj, pojo);
            GoodsCategory parent = this.goodsCategoryService.findById(Constants.MERCHANT_ID, parentId);
            pojo.setCategory(parent);
            pojo.setSpecs(specs);
            pojo.setAuxIds(auxs);
            goodsService.save(Constants.MERCHANT_ID, pojo);
        }
        return new RspResult();
    }


}
