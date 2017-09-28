package com.yiyou.repast.merchant.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
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
import com.yiyou.repast.merchant.model.MerchantRole;
import com.yiyou.repast.merchant.model.MerchantRoleMenu;
import com.yiyou.repast.merchant.service.IMerchantMenuService;
import com.yiyou.repast.merchant.service.IMerchantRoleService;

/**
 * 商户管理角色控制器
 * */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Reference
	private IMerchantRoleService merchantRoleService;
	@Reference
	private IMerchantMenuService merchantMenuService;

	@GetMapping()
	public String list(Model model) {
		return "/role/list";
	}
	
	@ResponseBody
	@PostMapping("/listData.do")
	public List<MerchantRole> listData(Integer page,Integer pageSize) {
		page=page==null?page=0:page;
		pageSize=Integer.MAX_VALUE;//客户端分页，服务端查询所有数据
		List<MerchantRole> data=merchantRoleService.findAll(Constants.MERCHANT_ID);
		return data;
	}

	@GetMapping("/edit")
	public String edit(Long id,Model model) {
		if(id==null) {
			return "/role/add";
		}
		model.addAttribute("obj",this.merchantRoleService.find(id));
		return "/role/edit";
	}
	
	@GetMapping("/pers")
	public String permission(Long id,Model model) {
		MerchantRole role=this.merchantRoleService.find(id);
		List<Long> menuIds=new ArrayList<>();
		if(!CollectionUtils.isEmpty(role.getRoleMenu())){
			menuIds=role.getRoleMenu().stream().map(MerchantRoleMenu::getMenuId).collect(Collectors.toList());
		}
		model.addAttribute("obj",role);
		model.addAttribute("menuIds",menuIds);
		model.addAttribute("menus", merchantMenuService.findAll(Constants.MERCHANT_ID));
		return "/role/permission";
	}
	
	@ResponseBody
	@PostMapping("/save.do")
	public RspResult save(MerchantRole obj,Long roleId) {
		if(obj.getId()==null) {
			obj.setMerchantId((Constants.MERCHANT_ID));
			merchantRoleService.save(obj);
		}else {
			MerchantRole pojo=merchantRoleService.find(obj.getId());
			RBeanUtils.copyProperties(obj, pojo);
			merchantRoleService.update(pojo);
		}
		return new RspResult();
	}
	
	@ResponseBody
	@PostMapping("/permission.do")
	public RspResult permissionSave(Long id,Long[] ids) {
		List<Long> menuIds=Arrays.asList(ids);
		merchantRoleService.updatePermission(id, menuIds);
		return new RspResult();
	}
	
	@ResponseBody
	@PostMapping("/validate.do")
	public RspResult validate(String name) {
		RspResult rsp=new RspResult();
		rsp.setData(this.merchantRoleService.findByName(Constants.MERCHANT_ID,name));
		return rsp;
	}
}
