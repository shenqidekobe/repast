package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.GoodsSpec;
import com.yiyou.repast.merchant.service.IGoodsSpecService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/goods/spec")
public class GoodsSpecController {

    @Reference
    private IGoodsSpecService goodsSpecService;

    @GetMapping()
    public String goodsSpec(Model model) {
        return "/goodsSpec/list";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<GoodsSpec> listData(Integer page, Integer pageSize) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        return goodsSpecService.findAll(Constants.MERCHANT_ID);
    }

    @ResponseBody
    @PostMapping("/save.do")
    public RspResult save(GoodsSpec obj, Long parentId, Long id) {
        if (obj == null) {
            return new RspResult(505, "参数错误");
        }
        goodsSpecService.save(Constants.MERCHANT_ID, obj);
        return new RspResult();
    }

    @GetMapping("/edit")
    public String edit(Long id, Model model) {
        return "/goodsSpec/add";
    }


    @GetMapping("/remove")
    public String delete(Long id, Model model) {
        this.goodsSpecService.remove(Constants.MERCHANT_ID, id);
        return "redirect:/goods/spec";
    }


}
