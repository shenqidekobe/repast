package com.yiyou.repast.elasticsearch.pojo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "article", type = "article", shards = 1, replicas = 0, refreshInterval = "-1")  
public class Article {

	@Id
	private String id;
	@Field
	private String title;
	@Field
	private String content;
	@Field
	private Date publishTime;

}
