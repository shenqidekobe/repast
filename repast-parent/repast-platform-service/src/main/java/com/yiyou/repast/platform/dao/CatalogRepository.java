package com.yiyou.repast.platform.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yiyou.repast.platform.model.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Integer>{
	
	@Query("select a from Catalog a where pid is null order by seq asc") 
	List<Catalog> findRootList();

}
