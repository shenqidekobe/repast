package com.yiyou.repast.search.service.impl;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.elasticsearch.pojo.Article;
import com.yiyou.repast.elasticsearch.service.ISearchService;
import com.yiyou.repast.search.dao.ElasticArticleRepository;

import repast.yiyou.common.util.DataGrid;

@Service
public class SearchImpl implements ISearchService{
	
	@Resource
	private ElasticArticleRepository elasticArticleRepository;

	@Override
	public void create(Article article) {
		elasticArticleRepository.save(article);
	}

	@Override
	public Article find(String id) {
		return elasticArticleRepository.findOne(id);
	}

	@Override
	public DataGrid<Article> findArticleList(String title, int page, int pageSize) {
		return null;
	}

}
