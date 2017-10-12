package com.yiyou.repast.rest.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiyou.repast.merchant.model.Goods;
import com.yiyou.repast.merchant.model.GoodsAux;
import com.yiyou.repast.merchant.service.IGoodsAuxService;
import com.yiyou.repast.merchant.service.IGoodsService;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.order.model.OrderItem;
import com.yiyou.repast.order.service.IOrderService;
import com.yiyou.repast.rest.base.AppResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import repast.yiyou.common.base.EnumDefinition.OrderStaus;
import repast.yiyou.common.util.DataGrid;

@Api(value="订单处理",tags="订单中心")
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private static ObjectMapper objectMapper=new ObjectMapper();
	
	@Reference
	private IOrderService orderService;
	@Reference
	private IGoodsService goodsService;
	@Reference
	private IGoodsAuxService goodsAuxService;
	
	@PostMapping("/list")
	@ApiOperation(value="订单列表",notes="待处理的订单列表查询接口，只查询今日订单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "merchantId", value = "商户ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "accountId", value = "登录帐号ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "deskNum", value = "桌号", required = false, dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态,例如await:待处理,awaitPay:待付款,settle:已结算,cancel:已取消", required = false, dataType = "String",defaultValue="await"),
		@ApiImplicitParam(name = "page", value = "页码，从0开始", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer") })
	public AppResult list(Long merchantId,String deskNum,String status,int page,int pageSize)throws Exception {
		if(StringUtils.isEmpty(status)) status=OrderStaus.await.name();
		OrderStaus orderStatus=OrderStaus.valueOf(OrderStaus.class, status);
		String startTime=DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		String endTime=DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		DataGrid<Order> data=orderService.findOrderList(merchantId, null, deskNum, orderStatus, startTime, endTime, page, pageSize);
		return new AppResult(objectMapper.writeValueAsString(data));
	}
	
	@PostMapping("/get/{id}")
	@ApiOperation(value="订单详情",notes="查询订单的详细信息，包括订单的每个项目items")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "accountId", value = "登录帐号ID", required = true, dataType = "Long")})
	@ApiResponses({@ApiResponse(code=4444,message="签名失败"),
			       @ApiResponse(code=4000,message="服务器异常")})
	public AppResult get(@PathVariable Long id) throws Exception{
		Order order=orderService.findById(id);
		return new AppResult(objectMapper.writeValueAsString(order));
	}
	

	@PostMapping("/update")
	@ApiOperation(value="更新订单",notes="更新订单的状态信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "status", value = "状态,awaitPay:待付款,cancel:已取消", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "accountId", value = "登录帐号ID", required = true, dataType = "Long")})
	public AppResult update(Long id,String status,Long accountId) throws Exception{
		OrderStaus orderStatus=OrderStaus.valueOf(OrderStaus.class, status);
		Order order=orderService.findById(id);
		order.setStatus(orderStatus);
		order=orderService.update(order);
		return new AppResult(objectMapper.writeValueAsString(order));
	}
	
	@PostMapping("/add/item")
	@ApiOperation(value="添加订单项",notes="给某个订单新增一个子项")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "count", value = "商品数量", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "specId", value = "规格ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "specName", value = "规格名称", required = true, dataType = "String"),
		@ApiImplicitParam(name = "auxIds", value = "辅料ID字符串，逗号分隔", required = true, dataType = "String"),
		@ApiImplicitParam(name = "accountId", value = "登录帐号ID", required = true, dataType = "Long")})
	public AppResult addItem(Long id,Long goodsId,Integer count,Long specId,String specName,String auxIds,Long accountId) throws Exception{
		Order order=this.orderService.findById(id);
		if(order==null) {return new AppResult(AppResult.OBJECT_NULL,"订单不存在");}
		Goods goods=goodsService.findById(null, goodsId);
		if(goods==null) {return new AppResult(AppResult.OBJECT_NULL,"商品不存在");}
		OrderItem item=new OrderItem();
		BigDecimal amount=goods.getAmount().multiply(new BigDecimal(count));
		item.setOrder(order);
		item.setGoodsId(goodsId);
		item.setGoodsName(goods.getName());
		item.setGoodsType(goods.getCategoryName());
		item.setCount(count);
		item.setAmount(amount);
		item.setSpecId(specId);
		item.setSpecName(specName);
		if(StringUtils.isNotEmpty(auxIds)) {
			item.setAuxIds(auxIds);
			List<Long> auIds=Arrays.asList(item.getAuxIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
			List<GoodsAux> auxList=goodsAuxService.findByIds(auIds);
			if(!org.springframework.util.CollectionUtils.isEmpty(auxList)) {
				List<String> names=auxList.stream().map(GoodsAux::getName).collect(Collectors.toList());
				item.setAuxNames(names.isEmpty()?null:StringUtils.join(names.toArray(),","));
			}
		}
		item.setStatus(OrderStaus.await);
		item.setCreateTime(new Date());
		item=orderService.saveOrderItem(item);
		//更新订单总数据
		order.setAmount(order.getAmount().add(amount));
		this.orderService.update(order);
		return new AppResult(objectMapper.writeValueAsString(item));
	}
	
	@PostMapping("/update/item")
	@ApiOperation(value="更新订单项的状态",notes="更新订单每个子项的状态信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "itemId", value = "订单项ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "status", value = "状态,ing:出菜中,already:已出菜,cancel:已取消\"", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "accountId", value = "登录帐号ID", required = true, dataType = "Long")})
	public AppResult updateItem(Long id,Long itemId,String status,Long accountId) throws Exception{
		OrderStaus orderStatus=OrderStaus.valueOf(OrderStaus.class, status);
		OrderItem item=this.orderService.findItemById(itemId);
		if(OrderStaus.already.equals(orderStatus)) {
			item.setServeTime(new Date());
		}
		item.setStatus(orderStatus);
		item=orderService.updateOrderItem(item);
		return new AppResult(objectMapper.writeValueAsString(item));
	}
	
	@PostMapping("/remove/item")
	@ApiOperation(value="删除订单项",notes="删除某个订单的某一个子项")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "itemId", value = "订单项ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "accountId", value = "登录帐号ID", required = true, dataType = "Long")})
	public AppResult removeItem(Long id,Long itemId,Long accountId) throws Exception{
		Order order=this.orderService.findById(id);
		if(order==null) {return new AppResult(AppResult.OBJECT_NULL,"订单不存在");}
		int size=order.getItems().size();
		OrderItem item=this.orderService.findItemById(itemId);
		orderService.removeOrderItem(item);
		
		if(size==1) {
			this.orderService.remove(id);
			return new AppResult();
		}
		//更新订单的总价
		order.setAmount(order.getAmount().subtract(item.getAmount()));
		this.orderService.update(order);
		return new AppResult();
	}
	


}
