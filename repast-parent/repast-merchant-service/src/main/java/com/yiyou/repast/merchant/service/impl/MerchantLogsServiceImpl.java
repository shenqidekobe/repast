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
import com.yiyou.repast.merchant.dao.MerchantLogsRepository;
import com.yiyou.repast.merchant.model.MerchantLogs;
import com.yiyou.repast.merchant.service.IMerchantLogsService;
import com.yiyou.repast.merchant.tools.PageConvertDataGrid;

import repast.yiyou.common.util.DataGrid;

@Service
public class MerchantLogsServiceImpl implements IMerchantLogsService {
	
	@Resource
	private MerchantLogsRepository merchantLogsRepository;

	@Override
	public MerchantLogs findById(Long merchantId, Long id) {
		MerchantLogs logs=merchantLogsRepository.findOne(id);
		if(logs==null||!logs.getMerchantId().equals(merchantId))return null;
		return logs;
	}

	@Override
	public List<MerchantLogs> findAll(Long merchantId) {
		return merchantLogsRepository.findByMerchantId(merchantId);
	}

	@Override
	public MerchantLogs save(Long merchantId, MerchantLogs obj) {
		obj.setMerchantId(merchantId);
		obj.setCreateTime(new Date());
		return merchantLogsRepository.save(obj);
	}

	@Override
	public void remove(Long merchantId, Long id) {
	}

	@Override
	public MerchantLogs update(Long merchantId, MerchantLogs obj) {
		return null;
	}

	@Override
	public DataGrid<MerchantLogs> findList(Long merchantId, int page, int pageSize) {
		MerchantLogs account=new MerchantLogs();
		if(merchantId!=null)account.setMerchantId(merchantId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<MerchantLogs> example = Example.of(account, matcher); 
	    Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "id");  
		Page<MerchantLogs> pages=merchantLogsRepository.findAll(example, pageable);
		return new PageConvertDataGrid.Bulid<MerchantLogs>().page(pages).build().getData();
	}


}
