package com.yiyou.repast.platform.service;

import com.yiyou.repast.merchant.model.Merchant;
import repast.yiyou.common.util.DataGrid;

/**
 * 商户管理api
 */
public interface IMerchantManageService {

    /**
     * 创建商户信息
     * */
    Merchant save(Merchant obj);

    /**
     * 按ID查询商户
     * */
    Merchant find(Long id);

    /**
     * 查找列表
     * @param page
     * @param size
     * @return
     */
    DataGrid<Merchant> findList(int page, int size);
}
