package com.yiyou.repast.merchant.controller;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.RBeanUtils;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.base.ThreadContextHolder;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.model.GoodsCategory;
import com.yiyou.repast.merchant.model.GoodsSpec;
import com.yiyou.repast.merchant.service.IGoodsAuxService;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.merchant.service.IGoodsSpecService;

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
        model.addAttribute("parentList", goodsCategoryService.findAll(ThreadContextHolder.getCurrentMerchantId()));
        model.addAttribute("specList", goodsSpecService.findAll(ThreadContextHolder.getCurrentMerchantId()));
        model.addAttribute("auxList", goodsAuxService.findAll(ThreadContextHolder.getCurrentMerchantId()));
        if (id == null) {
            return "/goods/add";
        }
        model.addAttribute("obj", goodsService.findById(ThreadContextHolder.getCurrentMerchantId(), id));
        return "/goods/edit";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<Goods> listData(Integer page, Integer pageSize) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        return goodsService.findAll(ThreadContextHolder.getCurrentMerchantId());
    }

    @ResponseBody
    @PostMapping("shelves/list")
    public List<Goods> shelvesData() {
        //所有上架商品
        return goodsService.findShelves(ThreadContextHolder.getCurrentMerchantId());
    }

    @GetMapping("/remove")
    public String delete(Long id, Model model) {
        Goods obj = goodsService.findById(ThreadContextHolder.getCurrentMerchantId(), id);
        obj.setShelves(false);
        goodsService.save(ThreadContextHolder.getCurrentMerchantId(), obj);
        return "redirect:/goods";
    }

    @ResponseBody
    @PostMapping("/save.do")
    public RspResult save(Goods obj, Long parentId, Long id,
                          @RequestParam(value = "auxsId", required = false) String auxIds,
                          @RequestParam(value = "specIds[]", required = false) List<Long> specIds) {
        if (obj == null) {
            return new RspResult(505, "参数错误");
        }
        Set<GoodsSpec> specs = goodsSpecService.findByIds(specIds);
        if (obj.getId() == null) {
            //新增
            GoodsCategory category = goodsCategoryService.findById(ThreadContextHolder.getCurrentMerchantId(), parentId);
            obj.setCategory(category);
            obj.setSpecs(specs);
            obj.setAuxIds(auxIds);
            obj.setShelves(true);
            goodsService.save(ThreadContextHolder.getCurrentMerchantId(), obj);
        } else {
            //保存
            Goods pojo = this.goodsService.findById(ThreadContextHolder.getCurrentMerchantId(), id);
            RBeanUtils.copyProperties(obj, pojo);
            GoodsCategory parent = this.goodsCategoryService.findById(ThreadContextHolder.getCurrentMerchantId(), parentId);
            pojo.setCategory(parent);
            pojo.setSpecs(specs);
            pojo.setAuxIds(auxIds);
            goodsService.save(ThreadContextHolder.getCurrentMerchantId(), pojo);
        }
        return new RspResult();
    }

}
