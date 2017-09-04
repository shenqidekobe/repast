package com.yiyou.repast.platform.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.platform.base.GlobalDefine;
import com.yiyou.repast.platform.model.Catalog;
import com.yiyou.repast.platform.model.GroupAccess;
import com.yiyou.repast.platform.service.ICatalogService;
import com.yiyou.repast.platform.service.IGroupAccessService;

import repast.yiyou.common.util.DataGrid;

/**
 * 后台功能菜单管理
 * 
 */
@Controller
@RequestMapping(value = "/admin/catalog")
public class AdminCatalogController extends BaseController {

	@Reference
	private ICatalogService catalogService;
	
	@Reference
	private IGroupAccessService groupAccessService;

	/**
	 * 后台功能菜单管理首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(Model model, HttpServletRequest request) {
		return "/admin/catalog/list";
	}

	/**
	 * 后台功能菜单管理首页
	 * */
	@RequestMapping(value = "listData", method = RequestMethod.POST)
	public String list(Model model, Integer page,String name,Integer pid,
			Integer pageSize, HttpServletRequest request) {
		// 下级菜单列表
		page=page==null?page=0:page-1;
		DataGrid<Catalog> catalogPage = catalogService.getCatalogList(pid, name, null, page,pageSize);
		model.addAttribute("dataPage", catalogPage);
		return "/admin/catalog/list_frag";
	}

	/**
	 * 进入后台管理菜单新建页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(Model model,Integer id, HttpServletRequest request) {
		if(id!=null){
			Catalog catalog = catalogService.getById(id);
			model.addAttribute("obj", catalog);
		}
		// 一级菜单列表
		DataGrid<Catalog> root = catalogService.getCatalogList(null, null,Catalog.TYPE_MENU, 0,555);
		List<Catalog> rootList=root.getRecords();
		model.addAttribute("catalogList", rootList);
		return "/admin/catalog/edit";
	}

	/**
	 * 后台管理菜单保存
	 * 
	 * @param catalog
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(Catalog catalog, Model model, HttpServletRequest request) {
		if(catalog.getId()==null){
			catalog.setCreateTime(new Date());
			catalogService.save(catalog);
		}else{
			Catalog oldCatalog = catalogService.getById(catalog.getId());
			BeanUtils.copyProperties(catalog, oldCatalog,new String[]{"createTime"});
			catalogService.update(oldCatalog);
		}
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}
	
	@RequestMapping(value = "/validateName")
	public void validateParam(String name, Integer id,HttpServletResponse rsp) {
		boolean valid = validateName(name, id);
		responsePrintResult(rsp, valid);
	}

	/**
	 * 验证名称是否重复
	 * */
	private boolean validateName(String name, Integer id) {
		boolean valid = true;
		if (id == null) {
			valid = catalogService.validateName(name);
		} else {
			Catalog obj = catalogService.getById(id);
			if (!obj.getName().equals(name)) {
				valid = catalogService.validateName(name);
			}
		}
		return valid;
	}

	/**
	 * 删除菜单
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public String remove(Integer id) {
		DataGrid<Catalog> data=this.catalogService.getCatalogList(id, null, null,0,55);
		if(data!=null&&!data.getRecords().isEmpty()){
			return GlobalDefine.JS_DEFINED.JS_RESULT.ERROR;
		}
		List<GroupAccess> list=this.groupAccessService.findGroupAccessByCatalogId(id);
		if(!list.isEmpty()){
			return GlobalDefine.JS_DEFINED.JS_RESULT.FAIL;
		}
		this.catalogService.removeById(id);
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}
	
	/**
	 * 图标查看页
	 */
	@RequestMapping(value = "icon")
	public String icon(Model model, HttpServletRequest request) {
		return "/admin/catalog/icon";
	}

}
