package com.yiyou.repast.platform.service;

import java.util.List;

import com.yiyou.repast.platform.model.Catalog;

import repast.yiyou.common.util.DataGrid;

public interface ICatalogService {
	
	Catalog save(Catalog obj);
	
	Catalog update(Catalog obj);

	Catalog getById(Integer id);

	int removeById(Integer id);
	
	boolean validateName(String name);
	
	List<Catalog> getRootCatalogList();

	DataGrid<Catalog> getCatalogList(Integer pid, String name, Integer type, int page,int size);
}
