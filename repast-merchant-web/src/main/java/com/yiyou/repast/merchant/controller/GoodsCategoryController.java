package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RBeanUtils;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.GoodsCategory;
import com.yiyou.repast.merchant.service.IGoodsCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import repast.yiyou.common.util.DataGrid;

import java.util.List;

@Controller
@RequestMapping("/goods/category")
public class GoodsCategoryController {

    @Reference
    private IGoodsCategoryService goodsCategoryService;

    @GetMapping()
    public String catagory(Model model) {
        return "/goodsCategory/list";
    }

    @ResponseBody
    @PostMapping("/listData.do")
    public List<GoodsCategory> catagoryListData(Integer page, Integer pageSize) {
        // TODO: 2017/9/11  分页没做
        page = page == null ? page = 0 : page;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        DataGrid<GoodsCategory> data = goodsCategoryService.findList(Constants.MERCHANT_ID, page, pageSize);
        return data.getRecords();
    }

    @GetMapping("/edit")
    public String edit(Long id, Model model) {
        model.addAttribute("parentList", this.goodsCategoryService.findAllParent(Constants.MERCHANT_ID));
        if (id == null) {
            return "/goodsCategory/add";
        }
        model.addAttribute("obj", this.goodsCategoryService.find(Constants.MERCHANT_ID, id));
        return "/goodsCategory/edit";
    }


    @ResponseBody
    @PostMapping("/save.do")
    public RspResult save(GoodsCategory obj, Long parentId) {
        if (obj != null) {
            if (parentId != null) {//是否选择上级
                GoodsCategory parent = goodsCategoryService.find(Constants.MERCHANT_ID, parentId);
                obj.setParent(parent);
            }
            if (obj.getId() == null) {
                goodsCategoryService.save(Constants.MERCHANT_ID, obj);
            } else {
                GoodsCategory pojo = goodsCategoryService.find(obj.getMerchantId(), obj.getId());
                RBeanUtils.copyProperties(obj, pojo);

                goodsCategoryService.update(Constants.MERCHANT_ID, pojo);
            }
            return new RspResult();
        } else {
            return new RspResult(505, "参数错误");
        }
    }


}