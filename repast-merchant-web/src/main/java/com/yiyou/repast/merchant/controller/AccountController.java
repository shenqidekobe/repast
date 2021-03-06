package com.yiyou.repast.merchant.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.merchant.base.RBeanUtils;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.base.ThreadContextHolder;
import com.yiyou.repast.merchant.model.MerchantAccount;
import com.yiyou.repast.merchant.service.IMerchantAccountService;
import com.yiyou.repast.merchant.service.IMerchantRoleService;

import repast.yiyou.common.base.EnumDefinition.AccountStaus;
import repast.yiyou.common.base.EnumDefinition.AccountType;
import repast.yiyou.common.util.DataGrid;
import repast.yiyou.common.util.Md5;

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

	@GetMapping()
	public String list(Model model) {
		return "/account/list";
	}
	
	@ResponseBody
	@PostMapping("/listData.do")
	public List<MerchantAccount> listData(String loginName,String status,String type,Integer page,Integer pageSize) {
		page=page==null?page=0:page;
		pageSize=Integer.MAX_VALUE;//客户端分页，服务端查询所有数据
		DataGrid<MerchantAccount> data=merchantAccountService.findList(ThreadContextHolder.getCurrentMerchantId(),loginName, status, type, page, pageSize);
		return data.getRecords();
	}

	@GetMapping("/edit")
	public String edit(Long id,Model model) {
		model.addAttribute("roleList", merchantRoleService.findAll(ThreadContextHolder.getCurrentMerchantId()));
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
		String password=Md5.getMD5(obj.getPassword());
		obj.setStatus(AccountStaus.normal);
		if(obj.getId()==null) {
			if(AccountType.manager.equals(obj.getType())) {
				if(merchantAccountService.findByLoginName(ThreadContextHolder.getCurrentMerchantId(),obj.getLoginName())!=null){
					return new RspResult(400,"该用户名已存在");
				}
			}else if(AccountType.terminal.equals(obj.getType())) {
				//终端用户全库唯一
				if(merchantAccountService.findByLoginName(null,obj.getLoginName())!=null){
					return new RspResult(400,"该账户已存在");
				}
			}
			obj.setPassword(password);
			obj.setMerchantId((ThreadContextHolder.getCurrentMerchantId()));
			merchantAccountService.save(obj);
		}else {
			MerchantAccount pojo=merchantAccountService.find(obj.getId());
            if(!pojo.getPassword().equals(obj.getPassword())) {
				obj.setPassword(password);
			}
            if(!pojo.getLoginName().equals(obj.getLoginName())) {
            	if(AccountType.manager.equals(obj.getType())) {
    				if(merchantAccountService.findByLoginName(ThreadContextHolder.getCurrentMerchantId(),obj.getLoginName())!=null){
    					return new RspResult(400,"该用户名已存在");
    				}
    			}else if(AccountType.terminal.equals(obj.getType())) {
    				//终端用户全库唯一
    				if(merchantAccountService.findByLoginName(null,obj.getLoginName())!=null){
    					return new RspResult(400,"该账户已存在");
    				}
    			}
            }
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
		rsp.setData(this.merchantAccountService.findByLoginName(ThreadContextHolder.getCurrentMerchantId(),loginName));
		return rsp;
	}
}
