package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.GoodsAux;
import com.yiyou.repast.merchant.service.IGoodsAuxService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        return goodsAuxService.findAll(Constants.MERCHANT_ID);
    }

    @ResponseBody
    @PostMapping("/save.do")
    public RspResult save(GoodsAux obj, Long parentId, Long id) {
        if (obj == null) {
            return new RspResult(505, "参数错误");
        }
        goodsAuxService.save(Constants.MERCHANT_ID, obj);
        return new RspResult();
    }

    @GetMapping("/edit")
    @Deprecated
    public String edit(Long id, Model model) {
        return "/goodsAux/list";
    }

    @GetMapping("/remove")
    public String delete(Long id, Model model) {
        this.goodsAuxService.remove(Constants.MERCHANT_ID, id);
        return "redirect:/goods/aux";
    }


}
