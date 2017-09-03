package com.yiyou.repast.platform.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.platform.controller.BaseController;
import com.yiyou.repast.platform.controller.base.GlobalDefine;
import com.yiyou.repast.platform.controller.base.RBeanUtils;
import com.yiyou.repast.platform.model.Admin;
import com.yiyou.repast.platform.model.Group;
import com.yiyou.repast.platform.service.IAdminService;
import com.yiyou.repast.platform.service.IGroupService;

import repast.yiyou.common.util.EncryptUtil;

/**
 * 后台用户管理
 * */
@RequestMapping("admin/admin")
@Controller
public class AdminUserController extends BaseController{

	Logger log = Logger.getLogger(AdminUserController.class);

	@Reference
	private IAdminService adminService;

	@Reference
	private IGroupService groupService;


	/**
	 * 后台用户管理首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(Model model, HttpServletRequest request) {
		return "/admin/admin/list";
	}

	/**
	 * 用户列表
	 * */
	@RequestMapping(value = "listData", method = RequestMethod.POST)
	public String listData(Model model, String loginName, String status,Integer page, Integer pageSize) {
		if (StringUtils.isEmpty(status))
			status = null;
		Page<Admin> adminPagination = adminService.getAdminList(null,
				loginName, status,  page,pageSize);
		
		Page<Group> groupPagination = groupService.findGroupList(null, 1,5555);
		Map<String,Group> groupMap = new HashMap<String,Group>();
		for(Group group : groupPagination.getContent()){
			groupMap.put(group.getId().toString(), group);
		}
		for(Admin admin: adminPagination.getContent()){
			Group group = groupMap.get(admin.getGroupId());
			if(group != null){
				admin.setGroupName(group.getName());
			}else{
				admin.setGroupName(admin.getGroupId());
			}
		}
		model.addAttribute("dataPagination", adminPagination);
		return "/admin/admin/list_frag";
	}

	/**
	 * 后台用户管理新增页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit")
	public String add(Model model,Integer id, HttpServletRequest request) {
		model.addAttribute("groupList", this.groupService.getGroupList());
		if(id!=null){
			model.addAttribute("obj", adminService.getById(id));
		}
		return "/admin/admin/edit";
	}

	/**
	 * 后台用户新增保存
	 * 
	 * @param admin
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(Admin admin, Model model, HttpServletRequest request) {
		boolean valid = validateLoginName(admin.getLoginName(), admin.getId());
		if(!valid){return GlobalDefine.JS_DEFINED.JS_RESULT.FAIL;}
		if(admin.getId()==null){
			admin.setPassword(EncryptUtil.getMD5(admin.getPassword()));
			admin.setStatus(String.valueOf(GlobalDefine.STATUS_YES));
			admin.setCreateTime(new Date());
			adminService.save(admin);
		}else{
			Admin oldAdmin = adminService.getById(admin.getId());
			if(!admin.getPassword().equals(oldAdmin.getPassword())){
				admin.setPassword(EncryptUtil.getMD5(admin.getPassword()));
			}
			RBeanUtils.copyProperties(admin, oldAdmin);
			adminService.update(oldAdmin);
		}
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateLoginName")
	public boolean validateParam(String loginName, Integer id,HttpServletResponse rsp) {
		boolean valid = validateLoginName(loginName, id);
		return valid;
	}

	/**
	 * 验证登录是否重复
	 * */
	private boolean validateLoginName(String loginName, Integer id) {
		boolean valid = true;
		if (id == null) {
			valid = adminService.validateLoginName(loginName);
		} else {
			Admin admin = adminService.getById(id);
			if (!admin.getLoginName().equals(loginName)) {
				valid = adminService.validateLoginName(loginName);
			}
		}
		return valid;
	}

	
	/**
	 * 禁用启用用户
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toggle", method = RequestMethod.POST)
	@ResponseBody
	public String toggle(Integer id, String status) {
		this.adminService.modifyStatus(id, status);
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}

	/**
	 * 重置用户密码
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/resetPass", method = RequestMethod.POST)
	@ResponseBody
	public String resetPass(String id) {
		String[] ids = StringUtils.split(id, ",");
		for (String i : ids) {
			Admin admin = this.adminService.getById(Integer.parseInt(i));
			admin.setPassword(EncryptUtil.getMD5("111111"));
			this.adminService.update(admin);
		}
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}

	/**
	 * 删除用户
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public String remove(Integer id, HttpServletRequest request) {
		Integer userId=getAdminUserId(request).getId();
		if(id.equals(userId)){
			return GlobalDefine.JS_DEFINED.JS_RESULT.ERROR;
		}
		this.adminService.removeAdmin(id);
		return GlobalDefine.JS_DEFINED.JS_RESULT.SUCCESS;
	}

}
