package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.base.ThreadContextHolder;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.util.DataGrid;

import java.util.Date;
import java.util.List;

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
		pageSize=Integer.MAX_VALUE;//客户端分页，服务端查询所有数据
		DataGrid<Order> data=orderService.findOrderList(ThreadContextHolder.getCurrentMerchantId(),
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
		if(as.equals(OrderStaus.cancel)) {
			obj.setCancelTime(new Date());
		}else {
			obj.setSettleTime(new Date());
		}
		obj.setStatus(as);
		orderService.update(obj);
		return new RspResult();
	}
	
}
