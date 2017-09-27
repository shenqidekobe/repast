package com.yiyou.repast.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.order.service.IOrderService;
import com.yiyou.repast.rest.base.AppResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="订单处理",tags="订单中心")
@RestController
@RequestMapping("api")
public class OrderController {
	
	@Reference
	private IOrderService orderService;
	
	@GetMapping("/index")
	@ApiOperation(value="首页查询",notes="这是一个非常广泛的查询接口，请注意调用")
	@ApiResponses(value= {@ApiResponse(code=4444,message="签名失败"),
			@ApiResponse(code=4000,message="服务器异常")})
	public String index(String name,String age,Integer sex) {
		return name+age+sex;
	}
	

	@GetMapping("/list")
	@ApiOperation(value="订单列表",notes="待处理的订单列表查询接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "页码，从0开始", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer") })
	public AppResult list(int page,int pageSize) {
		return new AppResult();
	}


}
