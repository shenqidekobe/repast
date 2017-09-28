package com.yiyou.repast.merchant.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.model.MerchantLogs;
import com.yiyou.repast.merchant.service.IMerchantLogsService;

import repast.yiyou.common.util.DataGrid;

/**
 * 商户操作日志控制器
 * */
@Controller
@RequestMapping("/logs")
public class MerchantLogsController {
	
	@Reference
	private IMerchantLogsService merchantLogsService;

	@GetMapping()
	public String list(Model model) {
		return "/logs/list";
	}
	
	@ResponseBody
	@PostMapping("/listData.do")
	public List<MerchantLogs> listData(String ip,Integer page,Integer pageSize) {
		page=page==null?page=0:page;
		pageSize=Integer.MAX_VALUE;//客户端分页，服务端查询所有数据
		DataGrid<MerchantLogs> data=merchantLogsService.findList(Constants.MERCHANT_ID, page, pageSize);
		return data.getRecords();
	}

	@GetMapping("/view/{id}")
	public String view(@PathVariable Long id,Model model) {
		MerchantLogs logs=this.merchantLogsService.findById(Constants.MERCHANT_ID,id);
		if(logs==null)return "error";
		model.addAttribute("obj",logs);
		return "/logs/view";
	}
}
