package com.yiyou.repast.platform.service;

import com.yiyou.repast.pay.model.PaymentRecord;
import repast.yiyou.common.util.DataGrid;

/**
 * 支付列表
 */
public interface IPayListService {

    /**
     * 商户支付记录
     *
     * @param merhantName 商户名
     * @param page
     * @param size
     * @return
     */
    DataGrid<PaymentRecord> findList(String merhantName, int page, int size);
}
