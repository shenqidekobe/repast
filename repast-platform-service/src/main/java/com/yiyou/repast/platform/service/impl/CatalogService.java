package com.yiyou.repast.platform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.platform.dao.CatalogRepository;
import com.yiyou.repast.platform.model.Catalog;
import com.yiyou.repast.platform.service.ICatalogService;
import com.yiyou.repast.platform.tools.PageConvertDataGrid;

import repast.yiyou.common.util.DataGrid;

@Service
public class CatalogService implements ICatalogService{
	
	@Resource
	private CatalogRepository catalogRepository;

	@Override
	public Catalog save(Catalog obj) {
		return catalogRepository.save(obj);
	}

	@Override
	public Catalog update(Catalog obj) {
		return catalogRepository.save(obj);
	}

	@Override
	public Catalog getById(Integer id) {
		return catalogRepository.findOne(id);
	}

	@Override
	public int removeById(Integer id) {
		catalogRepository.delete(id);
		return 0;
	}

	@Override
	public boolean validateName(String name) {
		Catalog catalog=new Catalog();
		catalog.setName(name);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Catalog> example = Example.of(catalog, matcher); 
	    List<Catalog> list=catalogRepository.findAll(example);
		return list.isEmpty();
	}

	@Override
	public List<Catalog> getRootCatalogList() {
		return catalogRepository.findRootList();
	}

	@Override
	public DataGrid<Catalog> getCatalogList(Integer pid, String name, Integer type,int page,int size) {
		Catalog catalog=new Catalog();
		if(!StringUtils.isEmpty(name))catalog.setName(name);
		if(pid!=null)catalog.setPid(pid);
		if(type!=null)catalog.setType(type);
	    ExampleMatcher matcher = ExampleMatcher.matching().
	    		withMatcher("name", GenericPropertyMatchers.contains()); //名称采用模糊匹配，withStringMatcher(StringMatcher.CONTAINING)
	    Example<Catalog> example = Example.of(catalog, matcher); 
	    Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");  
		Page<Catalog> pages=catalogRepository.findAll(example, pageable);
		return new PageConvertDataGrid.Bulid<Catalog>().page(pages).build().getData();
	}

}
