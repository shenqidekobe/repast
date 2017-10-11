package com.yiyou.repast.merchant.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.base.ThreadContextHolder;
import com.yiyou.repast.merchant.model.GoodsAux;
import com.yiyou.repast.merchant.service.IGoodsAuxService;

@Controller
@RequestMapping("/goods/aux")
public class GoodsAuxController {

    @Reference
    private IGoodsAuxService goodsAuxService;

    @GetMapping()
    public String goodsSpec(Model model) {
        return "/goodsAux/list";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<GoodsAux> listData(Integer page, Integer pageSize) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        return goodsAuxService.findAll(ThreadContextHolder.getCurrentMerchantId());
    }

    @ResponseBody
    @PostMapping("/save.do")
    public RspResult save(GoodsAux obj, Long parentId, Long id) {
        if (obj == null) {
            return new RspResult(505, "参数错误");
        }
        goodsAuxService.save(ThreadContextHolder.getCurrentMerchantId(), obj);
        return new RspResult();
    }

    @GetMapping("/edit")
    @Deprecated
    public String edit(Long id, Model model) {
        return "/goodsAux/list";
    }

    @GetMapping("/remove")
    public String delete(Long id, Model model) {
        this.goodsAuxService.remove(ThreadContextHolder.getCurrentMerchantId(), id);
        return "redirect:/goods/aux";
    }


}
