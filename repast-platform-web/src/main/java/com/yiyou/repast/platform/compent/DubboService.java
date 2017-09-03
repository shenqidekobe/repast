package com.yiyou.repast.platform.compent;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.platform.service.IAdminService;

@Component
public class DubboService {
	
	@Reference
	private IAdminService adminService;

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	

}
