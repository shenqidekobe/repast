package com.yiyou.repast.search.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.yiyou.repast.elasticsearch.pojo.Article;

public interface ElasticArticleRepository extends ElasticsearchRepository<Article, String> {


}
