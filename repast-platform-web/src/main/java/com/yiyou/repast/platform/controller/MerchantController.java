package com.yiyou.repast.platform.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.model.Merchant;
import com.yiyou.repast.merchant.service.IMerchantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repast.yiyou.common.util.DataGrid;

/**
 * 商家管理
 */
@Controller
@RequestMapping("merchant")
public class MerchantController extends BaseController {

    @Reference
    private IMerchantService merchantService;

    @RequestMapping()
    public String index() {
        return "admin/merchant/list";
    }

    /**
     * 商户列表
     */
    @RequestMapping(value = "listData", method = RequestMethod.POST)
    public String userList(Model model, Integer page, Integer pageSize) {
        page = page == null ? page = 0 : page - 1;
        pageSize = pageSize == null ? page = 10 : pageSize;
        DataGrid<Merchant> groupPage = merchantService.findList(page, pageSize);
        model.addAttribute("dataPage", groupPage);
        return "admin/merchant/list_frag";
    }

    /**
     * 修改帐户状态
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public Merchant edit(Merchant obj) {
        return merchantService.save(obj);
    }

}
