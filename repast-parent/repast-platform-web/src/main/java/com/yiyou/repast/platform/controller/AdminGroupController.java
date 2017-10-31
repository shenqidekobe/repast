package com.yiyou.repast.platform.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.platform.base.GlobalDefine;
import com.yiyou.repast.platform.model.Admin;
import com.yiyou.repast.platform.model.Catalog;
import com.yiyou.repast.platform.model.Group;
import com.yiyou.repast.platform.model.GroupAccess;
import com.yiyou.repast.platform.service.IAdminService;
import com.yiyou.repast.platform.service.ICatalogService;
import com.yiyou.repast.platform.service.IGroupAccessService;
import com.yiyou.repast.platform.service.IGroupService;

import repast.yiyou.common.util.DataGrid;

/**
 * 后台分组管理
 * 
 */
@Controller
@RequestMapping(value = "/admin/group")
public class AdminGroupController extends BaseController {

	@Reference
	private IAdminService adminService;

	@Reference
	private IGroupService groupService;

	@Reference
	private IGroupAccessService groupAccessService;

	@Reference
	private ICatalogService catalogService;

	/**
	 * 后台角色管理首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(Model model, HttpServletRequest request) {
		return "admin/group/list";
	}

	/**
	 * 角色列表
	 * */
	@RequestMapping(value = "listData", method = RequestMethod.POST)
	public String list(Model model,String name,Integer page,Integer pageSize) {
		page=page==null?page=0:page-1;
		DataGrid<Group> groupPage = groupService.findGroupList(name,page,pageSize);
		model.addAttribute("dataPage", groupPage);
		return "admin/group/list_frag";
	}

	/**
	 * 进入后台角色新建页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(Model model, Integer id, HttpServletRequest request) {
		if (id != null){
			Group group = groupService.getById(id);
			model.addAttribute("obj", group);
		}
		return "admin/group/edit";
	}

	/**
	 * 后台角色保存
	 * 
	 * @param role
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(Group group, Model model, HttpServletRequest request) {
		if(group.getId()==null){
			group.setCreateTime(new Date());
			groupService.save(group);
		}else{
			Group oldGroup = groupService.getById(group.getId());
			oldGroup.setName(group.getName());
			oldGroup.setDescription(group.getDescription());
			groupService.update(oldGroup);
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
			valid = groupService.validateName(name);
		} else {
			Group obj = groupService.getById(id);
			if (!obj.getName().equals(name)) {
				valid = groupService.validateName(name);
			}
		}
		return valid;
	}


	/**
	 * 删除角色
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public String remove(Integer id, HttpServletRequest request) {
		List<Admin> userList=this.adminService.getByGroupId(id);
		if(!userList.isEmpty()){
			return GlobalDefine.JS_DEFINED.JS_RESULT.ERROR;
		}
		this.groupService.removeById(id);
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}

	/**
	 * 后台角色分配权限
	 * 
	 * @param group
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/distribute")
	public String distribute(Model model,Integer id,HttpServletRequest request) {
		Group group = groupService.getById(id);
		DataGrid<Catalog> catalogPage = catalogService.getCatalogList(null, null, Integer.valueOf(GlobalDefine.STATUS_YES), 0,555);
		DataGrid<GroupAccess> groupAccessPage = groupAccessService.findGroupAccessList(id, 0,555);

		for (Catalog catalog : catalogPage.getRecords()) {
			for (GroupAccess groupAccess : groupAccessPage.getRecords()) {
				if (groupAccess.getCatalogId().intValue() == catalog.getId()
						.intValue()) {
					catalog.setDistributed(true);
					break;
				}
			}
		}
		model.addAttribute("obj", group);
		model.addAttribute("dataPage", catalogPage);
		return "admin/group/distribute";
	}


	/**
	 * 后台用户组分配权限保存
	 * 
	 * @param group
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/distribute/save")
	@ResponseBody
	public String distribute_save(@RequestParam(required = true) Integer id,
			Model model, HttpServletRequest request) {
		String catalogIds = request.getParameter("catalogIds");
		// 删掉原来该用户组的权限
		DataGrid<GroupAccess> groupAccessPage = groupAccessService
				.findGroupAccessList(id, 0,555);
		for (GroupAccess ga : groupAccessPage.getRecords()) {
			groupAccessService.remove(ga);
		}
		// 保存该用户组新的权限
		if (catalogIds != null && !catalogIds.equals(",")) {
			for (String cid : catalogIds.split(",")) {
				GroupAccess ga = new GroupAccess();
				ga.setCatalogId(Integer.valueOf(cid));
				ga.setGroupId(id);
				groupAccessService.save(ga);
			}
		}
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}
}
