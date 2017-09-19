package com.yiyou.repast.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.weixin.service.GoodsBusinessService;

/**
 * 商品查询处理
 * */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Resource
	private GoodsBusinessService goodsBusinessService;

	
	@GetMapping("/index")
	public String index(Integer pc,String pt,Model model) {
		model.addAttribute("pc", pc);
		model.addAttribute("pt", pt);
		return "goods/list";
	}
	
	@PostMapping("/listData.do")
	public String listData(String type,Model model) {
		List<Goods> list=goodsBusinessService.findGoodsList();
		Map<String, List<Goods>> map=new HashMap<>();
		map.put("打混", list);
		map.put("消魂", list);
		model.addAttribute("dataMap", map);
		return "goods/list_frag";
	}
	
	@GetMapping("/detail")
	public String detail(Long id,Model model) {
		Goods obj=goodsBusinessService.findGoodsById(id);
		model.addAttribute("obj", obj);
		return "goods/detail";
	}

}
