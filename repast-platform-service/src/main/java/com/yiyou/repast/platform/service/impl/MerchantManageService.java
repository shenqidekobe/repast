package com.yiyou.repast.platform.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.model.Merchant;
import com.yiyou.repast.platform.dao.MerchantRepository;
import com.yiyou.repast.platform.service.IMerchantManageService;
import com.yiyou.repast.platform.tools.PageConvertDataGrid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MerchantManageService implements IMerchantManageService {

    @Resource
    private MerchantRepository merchantRepository;

    @Override
    public Merchant save(Merchant obj) {
        obj.setAuditTime(new Date());//设置审核时间
        if (obj.getId() == null) {
            obj.setCreateTime(new Date());
        }
        return merchantRepository.save(obj);
    }

    @Override
    public Merchant find(Long id) {
        return merchantRepository.findOne(id);
    }

    @Override
    public DataGrid<Merchant> findList(int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<Merchant> pages = merchantRepository.findAll(pageable);
        return new PageConvertDataGrid.Bulid<Merchant>().page(pages).build().getData();
    }
}
