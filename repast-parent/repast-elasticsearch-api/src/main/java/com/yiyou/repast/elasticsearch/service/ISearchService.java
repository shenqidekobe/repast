package com.yiyou.repast.elasticsearch.service;

import com.yiyou.repast.elasticsearch.pojo.Article;

import repast.yiyou.common.util.DataGrid;

public interface ISearchService {

	void create(Article article);
	
	Article find(String id);
	
	DataGrid<Article> findArticleList(String title,int page,int pageSize);
}
