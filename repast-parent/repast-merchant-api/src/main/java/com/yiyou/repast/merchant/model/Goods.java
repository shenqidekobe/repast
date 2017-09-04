package com.yiyou.repast.merchant.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品
 */
@Entity
@Table(name = "t_goods")
public class Goods implements Serializable{

	private static final long serialVersionUID = -5737781723933462465L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long merchantId;//商户ID
}
