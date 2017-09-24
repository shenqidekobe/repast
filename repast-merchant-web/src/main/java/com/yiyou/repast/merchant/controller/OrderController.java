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
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.service.IOrderService;

import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.util.DataGrid;

/**
 * 订单管理控制器
 * */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Reference
	private IOrderService orderService;

	@GetMapping()
	public String list(Model model) {
		return "/order/list";
	}
	
	@ResponseBody
	@PostMapping("/listData.do")
	public List<Order> listData(String orderId,String deskNum,OrderStaus status,
			String startTime,String endTime,Integer page,Integer pageSize) {
		page=page==null?page=0:page;
		pageSize=pageSize==null?pageSize=10:pageSize;
		DataGrid<Order> data=orderService.findOrderList(Constants.MERCHANT_ID,
				orderId, deskNum, status, startTime, endTime, page, pageSize);
		return data.getRecords();
	}

	@GetMapping("/view/{id}")
	public String view(@PathVariable Long id,Model model) {
		Order order=orderService.findById(id);
		model.addAttribute("obj",order);
		return "/order/view";
	}
	
	
	@ResponseBody
	@PostMapping("/toggle.do")
	public RspResult toggle(Long id,String status) {
		Order obj= orderService.findById(id);
		OrderStaus as=OrderStaus.valueOf(OrderStaus.class, status);
		obj.setStatus(as);
		orderService.update(obj);
		return new RspResult();
	}
	
}
