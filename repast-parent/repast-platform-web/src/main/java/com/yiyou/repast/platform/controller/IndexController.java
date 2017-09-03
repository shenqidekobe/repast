package com.yiyou.repast.platform.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.platform.base.GlobalDefine;
import com.yiyou.repast.platform.base.Navbar;
import com.yiyou.repast.platform.base.Navbar.Children;
import com.yiyou.repast.platform.base.SystemInfo;
import com.yiyou.repast.platform.model.Admin;
import com.yiyou.repast.platform.model.Catalog;
import com.yiyou.repast.platform.model.GroupAccess;
import com.yiyou.repast.platform.service.IAdminService;
import com.yiyou.repast.platform.service.ICatalogService;
import com.yiyou.repast.platform.service.IGroupAccessService;

import repast.yiyou.common.util.EncryptUtil;

/**
 * 应用首页
 * */
@RequestMapping("admin")
@Controller
public class IndexController extends BaseController {

	@Reference
	private IAdminService adminService;
	@Reference
	private ICatalogService catalogService;
	@Reference
	private IGroupAccessService groupAccessService;
	
	/**
	 * 进入首页
	 * */
	@RequestMapping("index")
	public String index(HttpServletRequest req, Model model) {
		Admin obj = (Admin) req.getSession().getAttribute(GlobalDefine.SESSION_LOGIN_ADMIN);
		if (obj == null) {
			return "redirect:/admin";
		}
		return "/admin/index";
	}
	
	/**
	 * 首页内容
	 * */
	@RequestMapping("main")
	public String main(HttpServletRequest request, Model model) {
		//系统消息代码
		SystemInfo system=SystemInfo.getInstance(request);
		model.addAttribute("system", system);
		model.addAttribute("allCount",111);
		return "/admin/main";
	}
	
	/**
	 * 获取用户信息分布图
	 * */
	@ResponseBody
	@RequestMapping("main/pie")
	public Map<String,BigDecimal> pie(Model model,HttpServletResponse rsp) {
		Map<String,BigDecimal> pieMap=new HashMap<String,BigDecimal>();
		return pieMap;
	}
	
	
	/**
	 * 进入登录页面
	 * */
	@RequestMapping("")
	public String login(HttpServletRequest req, Model model) {
		Admin obj = (Admin) req.getSession().getAttribute(GlobalDefine.SESSION_LOGIN_ADMIN);
		if (obj != null) {
			return "redirect:/admin/index";
		}
		return "/admin/login";
	}
	
	/**
	 * 进入登录页面
	 * */
	@ResponseBody
	@RequestMapping("startLogin")
	public String startLogin(String loginName,String password, HttpServletRequest request) {
		Admin obj=this.adminService.find(loginName, password);
		if(obj==null)return GlobalDefine.JS_DEFINED.JS_RESULT.FAIL;
		//判断是否有权限登录
		Page<GroupAccess> gaPagin=groupAccessService.findGroupAccessList(Integer.valueOf(obj.getGroupId()),0,555);
		if(gaPagin.getContent().isEmpty())return GlobalDefine.JS_DEFINED.JS_RESULT.ERROR;
		
		request.getSession(true).setAttribute(GlobalDefine.SESSION_LOGIN_ADMIN, obj);
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}
	
	/**
	 * 用户权限信息
	 * */
	@ResponseBody
	@RequestMapping("navbar")
	public List<Navbar> navbar(HttpServletRequest req) {
		Admin obj = (Admin) req.getSession().getAttribute(GlobalDefine.SESSION_LOGIN_ADMIN);
		if (obj == null) {
			return null;
		}
		//查询属于当前用户的角色对应的菜单列表，并对其seq进行排序
	    Page<GroupAccess> gaPagin=groupAccessService.findGroupAccessList(Integer.valueOf(obj.getGroupId()), 1,555);
	    List<Catalog> catalogList=new ArrayList<Catalog>();
	    for(GroupAccess ga:gaPagin.getContent()){
	    	Integer catalogId=ga.getCatalogId();
	    	Catalog catalog=this.catalogService.getById(catalogId);
	    	if(Catalog.TYPE_MENU==catalog.getType()){
	    		catalogList.add(catalog);
	    	}
	    }
	    //排序方法  
        Collections.sort(catalogList, new Comparator<Catalog>() {
            public int compare(Catalog o1,Catalog o2) {   
                return o1.getSeq().compareTo(o2.getSeq());
            }
        });
        List<Catalog> rootList=this.catalogService.getRootCatalogList();
        List<Navbar> navList=new ArrayList<Navbar>();
        boolean spread=true; 
        for(Catalog ca:rootList){
        	boolean isSub=false;
    		Navbar navbar=new Navbar(ca.getName(), ca.getIcon(), spread);
    		//取出子集
    		List<Children> children=new ArrayList<Navbar.Children>();
    		for(Catalog chlid:catalogList){
    			 if(chlid.getPid()!=null&&chlid.getPid()==ca.getId()){
    				 children.add(new Navbar.Children(chlid.getName(), chlid.getIcon(), chlid.getUrl()));
    				 isSub=true;
    			 }
    		}
    		if(isSub){
    			navbar.setChildren(children);
    			navList.add(navbar);
    			spread=false;
    		}
        }
        return navList;
	}
	
	/**
	 * 设置界面
	 * */
	@RequestMapping("setting")
	public String setting(HttpServletRequest req, Model model) {
		Integer id=getAdminUserId(req);
		Admin obj=this.adminService.getById(id);
		model.addAttribute("obj",obj);
		return "/admin/setting";
	}
	
	/**
	 * 设置信息保存
	 * */
	@ResponseBody
	@RequestMapping("setting/save")
	public String settingSave(String nickName,String password, String newPassword,String newPassword1,HttpServletRequest req) {
		Integer id=getAdminUserId(req);
		Admin obj=this.adminService.getById(id);
		if(obj==null)return GlobalDefine.JS_DEFINED.JS_RESULT.FAIL;
		if(newPassword==null||newPassword1==null||!newPassword.equals(newPassword1)){
			return GlobalDefine.JS_DEFINED.JS_RESULT.FAIL;
		}
		String encryPwd = EncryptUtil.getMD5(password);
		if(!obj.getPassword().equals(encryPwd)){
			return GlobalDefine.JS_DEFINED.JS_RESULT.ERROR;
		}
		obj.setNickName(nickName);
		obj.setPassword(EncryptUtil.getMD5(newPassword));
		this.adminService.update(obj);
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}
	
	
	/**
	 * 进入登出页面
	 * */
	@RequestMapping("logout")
	public String logout(HttpServletRequest req, Model model) {
		req.getSession().invalidate();
		return "redirect:/admin";
	}

}
