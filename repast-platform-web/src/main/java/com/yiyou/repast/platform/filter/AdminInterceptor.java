package com.yiyou.repast.platform.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.platform.base.GlobalDefine;
import com.yiyou.repast.platform.model.Admin;
import com.yiyou.repast.platform.model.Catalog;
import com.yiyou.repast.platform.model.GroupAccess;
import com.yiyou.repast.platform.service.ICatalogService;
import com.yiyou.repast.platform.service.IGroupAccessService;

import repast.yiyou.common.util.DataGrid;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	@Reference
	private ICatalogService catalogService;

	@Reference
	private IGroupAccessService groupAccessService;
	

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		System.out.println("------------------------------------99999999999999999999--------------" + request.getRequestURI());
		request.setAttribute("_PATH", request.getContextPath());
		String reqURI = request.getRequestURI();

		if (reqURI.contains("/admin")) {
			if (reqURI.endsWith("admin/") || reqURI.endsWith("admin")||reqURI.endsWith("startLogin")) {
				return true;
			}
			// 未登录
			Admin adminPreferences = (Admin) request.getSession().getAttribute(GlobalDefine.SESSION_LOGIN_ADMIN);
			if (adminPreferences == null) {
				response.sendRedirect(request.getContextPath()+"/admin");
				return false;
			}
			
			DataGrid<Catalog> catalogPagination = catalogService.getCatalogList(null, reqURI, null, 1,555);
			// 该请求不在权限控制列表内，则直接忽略
			if (catalogPagination.getRecords() == null || catalogPagination.getRecords().size() == 0) {
				return true;
			} else {
				//该请求在权限控制列表内，则判断用户所在组的权限点
				Catalog ctl = catalogPagination.getRecords().get(0);

				String groupIds = adminPreferences.getGroupId();
				// 当前用户尚未分组，不允许访问
				if (StringUtils.isEmpty(groupIds)) {
					return false;
				} else {
					// 当前用户已分组，取出其拥用的操作权限�?
					DataGrid<GroupAccess> groupAccessPagination = groupAccessService.findGroupAccessListByGroupIds(groupIds, 1,5555);
					for (GroupAccess groupAccess : groupAccessPagination.getRecords()) {
						if (groupAccess.getCatalogId().intValue() == ctl.getId().intValue()) {
							return true;
						}
					}
					return false;
				}
			}
			
		}
		return true;
	}

}
