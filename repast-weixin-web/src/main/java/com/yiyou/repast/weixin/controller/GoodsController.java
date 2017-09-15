package com.yiyou.repast.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品查询处理
 * */
@Controller
@RequestMapping("/goods")
public class GoodsController {

	
	@GetMapping("/index")
	public String index(Integer pc,String pt) {
		return "/goods/list";
	}

}
