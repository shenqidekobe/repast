package com.yiyou.repast.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiyou.repast.rest.base.AppResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "商户接口", tags = "商户")
@RestController
@RequestMapping("api")
public class MerchantController {

	@GetMapping("/view/{id}")
	@ApiOperation(value = "商户信息", notes = "")
	@ApiImplicitParam(name = "id", value = "商户ID", required = true, dataType = "Long")
	public AppResult list(@PathVariable Long id) {
		return new AppResult();
	}

}
