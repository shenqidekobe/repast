package com.yiyou.repast.merchant.controller;

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
import com.yiyou.repast.merchant.model.RecommendGoods;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.merchant.service.IRecommendService;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

    @Reference
    private IRecommendService recommendService;
    @Reference
    private IGoodsService goodsService;
    @Reference
    private IGoodsCategoryService goodsCategoryService;

    @GetMapping()
    public String recommendManager(Model model) {
        model.addAttribute("categoryList", goodsCategoryService.findAll(ThreadContextHolder.getCurrentMerchantId()));
        return "/goodsRecommend/list";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<RecommendGoods> listData(Integer page, Integer pageSize, String date) {
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        return recommendService.findAll(ThreadContextHolder.getCurrentMerchantId());
    }


    @PostMapping("/edit")
    @ResponseBody
    public RspResult edit(Long id, @RequestParam(value = "goodsIds[]", required = false) List<Long> goodsIds, Model model) {
        if (id != null) { //编辑
            RecommendGoods recommend = recommendService.findById(ThreadContextHolder.getCurrentMerchantId(), id);
            RspResult result = new RspResult();
            result.setData(recommend);
            return result;
        } else {
            try {
                recommendService.upDate(ThreadContextHolder.getCurrentMerchantId(), goodsIds);
            } catch (Exception e) {
                e.printStackTrace();
                return new RspResult(505, "");
            }
            return new RspResult();
        }
    }

    @PostMapping("/remark")
    @ResponseBody
    public RspResult remark(Model model, Long recommendId, String remark) {
        recommendService.editRemark(ThreadContextHolder.getCurrentMerchantId(), recommendId, remark);
        return new RspResult();
    }
}
