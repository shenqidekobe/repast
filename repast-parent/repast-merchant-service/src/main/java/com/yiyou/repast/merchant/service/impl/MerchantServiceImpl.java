package com.yiyou.repast.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantRepository;
import com.yiyou.repast.merchant.model.Merchant;
import com.yiyou.repast.merchant.service.IMerchantService;
import com.yiyou.repast.merchant.tools.PageConvertDataGrid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import repast.yiyou.common.util.DataGrid;

import javax.annotation.Resource;

@Service
public class MerchantServiceImpl implements IMerchantService {
	
	@Resource
	private MerchantRepository merchantRepository;

	@Override
	public Merchant save(Merchant obj) {
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
