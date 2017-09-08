package com.yiyou.repast.merchant.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RBeanUtils;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.service.IMerchantAccountService;
import com.yiyou.repast.merchant.service.IMerchantRoleService;
import com.yiyou.repast.merchant.service.IMerchantService;

import repast.yiyou.common.base.EnumDefinition.AccountStaus;
import repast.yiyou.common.util.DataGrid;

/**
 * 商户管理员控制器
 * */
@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Reference
	private IMerchantAccountService merchantAccountService;
	@Reference
	private IMerchantRoleService merchantRoleService;
	@Reference
	private IMerchantService merchantService;

	@GetMapping()
	public String list(Model model) {
		return "/account/list";
	}
	
	@ResponseBody
	@PostMapping("/listData.do")
	public List<MerchantAccount> listData(String loginName,String status,String type,Integer page,Integer pageSize) {
		page=page==null?page=0:page;
		pageSize=pageSize==null?pageSize=10:pageSize;
		DataGrid<MerchantAccount> data=merchantAccountService.findList(Constants.MERCHANT_ID,loginName, status, type, page, pageSize);
		return data.getRecords();
	}

	@GetMapping("/edit")
	public String edit(Long id,Model model) {
		model.addAttribute("roleList", merchantRoleService.findAll(Constants.MERCHANT_ID));
		if(id==null) {
			return "/account/add";
		}
		model.addAttribute("obj",this.merchantAccountService.find(id));
		return "/account/edit";
	}
	
	@ResponseBody
	@PostMapping("/save.do")
	public RspResult save(MerchantAccount obj,Long roleId) {
		if(roleId!=null) {
			obj.setRole(this.merchantRoleService.find(roleId));
		}
		obj.setStatus(AccountStaus.normal);
		if(obj.getId()==null) {
			obj.setMerchant(merchantService.find(Constants.MERCHANT_ID));
			merchantAccountService.save(obj);
		}else {
			MerchantAccount pojo=merchantAccountService.find(obj.getId());
			RBeanUtils.copyProperties(obj, pojo);
			merchantAccountService.update(pojo);
		}
		return new RspResult();
	}
	
	@ResponseBody
	@PostMapping("/toggle.do")
	public RspResult toggle(Long id,String status) {
		MerchantAccount obj= merchantAccountService.find(id);
		AccountStaus as=AccountStaus.valueOf(AccountStaus.class, status);
		obj.setStatus(as);
		merchantAccountService.update(obj);
		return new RspResult();
	}
	
	@ResponseBody
	@PostMapping("/validate.do")
	public RspResult validate(String loginName) {
		RspResult rsp=new RspResult();
		rsp.setData(this.merchantAccountService.findByLoginName(Constants.MERCHANT_ID,loginName));
		return new RspResult();
	}
}
