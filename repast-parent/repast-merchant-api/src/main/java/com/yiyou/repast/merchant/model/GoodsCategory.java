package com.yiyou.repast.merchant.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品分类
 */
@Entity
@Table(name = "t_goods_category")
public class GoodsCategory implements Serializable {

	private static final long serialVersionUID = 1801573277087560343L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;//商户ID
}
