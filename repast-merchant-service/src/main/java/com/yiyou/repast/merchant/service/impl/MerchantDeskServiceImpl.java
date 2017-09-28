package com.yiyou.repast.merchant.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.MerchantDeskRepository;
import com.yiyou.repast.merchant.model.MerchantDesk;
import com.yiyou.repast.merchant.service.IMerchantDeskService;
import com.yiyou.repast.merchant.tools.PageConvertDataGrid;

import repast.yiyou.common.util.DataGrid;

@Service
public class MerchantDeskServiceImpl implements IMerchantDeskService {
	
	@Resource
	private MerchantDeskRepository merchantDeskRepository;

	@Override
	public MerchantDesk findById(Long merchantId, Long id) {
		return merchantDeskRepository.findOne(id);
	}

	@Override
	public List<MerchantDesk> findAll(Long merchantId) {
		return merchantDeskRepository.findByMerchantId(merchantId);
	}

	@Override
	public MerchantDesk save(Long merchantId, MerchantDesk obj) {
		obj.setCreateTime(new Date());
		obj.setMerchantId(merchantId);
		return merchantDeskRepository.save(obj);
	}

	@Override
	public void remove(Long merchantId, Long id) {
		merchantDeskRepository.delete(id);		
	}

	@Override
	public MerchantDesk update(Long merchantId, MerchantDesk obj) {
		return merchantDeskRepository.save(obj);
	}

	@Override
	public DataGrid<MerchantDesk> findList(Long merchantId, int page, int pageSize) {
		MerchantDesk account=new MerchantDesk();
		if(merchantId!=null)account.setMerchantId(merchantId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantDesk> example = Example.of(account, matcher); 
	    Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "id");  
		Page<MerchantDesk> pages=merchantDeskRepository.findAll(example, pageable);
		return new PageConvertDataGrid.Bulid<MerchantDesk>().page(pages).build().getData();
	}

}
