package com.yiyou.repast.platform.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.yiyou.repast.platform.model.Catalog;

public interface ICatalogService {
	
	Catalog save(Catalog obj);
	
	Catalog update(Catalog obj);

	Catalog getById(Integer id);

	int removeById(Integer id);
	
	boolean validateName(String name);
	
	List<Catalog> getRootCatalogList();

	Page<Catalog> getCatalogList(Integer pid, String name, Integer type, int page,int size);
}
