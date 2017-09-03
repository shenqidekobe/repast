package com.yiyou.repast.platform.dubbo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.platform.dao.CatalogRepository;
import com.yiyou.repast.platform.model.Catalog;
import com.yiyou.repast.platform.service.ICatalogService;

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
		return null;
	}

	@Override
	public Page<Catalog> getCatalogList(Integer pid, String name, Integer type,int page,int size) {
		return null;
	}

}
