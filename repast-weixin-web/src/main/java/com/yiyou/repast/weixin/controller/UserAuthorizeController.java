package com.yiyou.repast.weixin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dw.weixin.sdk.openapi.IWeixinBasisAPI;
import com.dw.weixin.sdk.request.template.WxTemplateMsgRequest;
import com.yiyou.repast.merchant.model.UserAuthorizeApply;
import com.yiyou.repast.weixin.base.RspResult;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.compent.WechatProperties;
import com.yiyou.repast.weixin.service.UserBusinessService;

import repast.yiyou.common.base.EnumDefinition.AuthorizeAuditStaus;

/**
 * 用户申请授权，部门领导签字才可开始点菜
 * */
@Controller
public class UserAuthorizeController {
	
	@Resource
	private UserBusinessService userService;
	@Resource
	private IWeixinBasisAPI weixinBasisAPI;
	@Resource
	private WechatProperties wechatProperties;
	
	/**
	 * 开始申请授权
	 * */
	@GetMapping("/authorize")
	public String apply() {
		return "/authorize/apply";
	}
	
	/**
	 * 保存授权记录，并且微信通知领导进行授权
	 * */
	@ResponseBody
	@PostMapping("authorize/apply/save.do")
	public RspResult applySave(UserAuthorizeApply obj,HttpServletRequest request) {
		RspResult rsp=new RspResult();
		SessionToken session=userService.getSessionUser();
		obj.setUserId(session.getUserId());
		obj.setUserName(session.getUserName());
		obj.setMerchantId(session.getMerchantId());
		obj=userService.createUserAuthorizeApply(obj);
		rsp.setData(obj.getId());
		//微信模版消息通知签字人
		try {
			WxTemplateMsgRequest treq=new WxTemplateMsgRequest();
			String auditUrl=wechatProperties.getDomain()+request.getContextPath()+"/auxt/"+obj.getId();
			Map<String,Map<String,String>> data=new HashMap<String, Map<String,String>>();
			Map<String,String> map=new HashMap<String,String>();
			map.put("value", "您好！你有新的审核任务");
			map.put("color", "#173177");
			data.put("first", map);//first
			
			map=new HashMap<String,String>();
			map.put("value", obj.getCompany());
			map.put("color", "#173177");
			data.put("keyword1", map);//公司名
			
			map=new HashMap<String,String>();
			map.put("value", session.getPhone());
			map.put("color", "#173177");
			data.put("keyword2", map);//联系方式
			
			map=new HashMap<String,String>();
			map.put("value", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			map.put("color", "#173177");
			data.put("keyword3", map);//审核时间
			
			map=new HashMap<String,String>();
			map.put("value", "吃饭申请");
			map.put("color", "#173177");
			data.put("keyword4", map);//审核类型
			
			map=new HashMap<String,String>();
			map.put("value", obj.getDept());
			map.put("color", "#173177");
			data.put("keyword5", map);//所属机构
			
			map=new HashMap<String,String>();
			map.put("value", "点击消息立即进行审核");
			map.put("color", "#173177");
			data.put("remark", map);//remark
			treq.setTemplate_id(wechatProperties.getNoticeTemplateId());
			treq.setData(data);
			treq.setUrl(auditUrl);
			weixinBasisAPI.sendReq(treq);
		} catch (Exception e) {
		}
		return rsp;
	}
	

	/**
	 * 领导进入审批
	 * */
	@GetMapping("/auxt/{id}")
	public String audit(Model model,@PathVariable Long id) {
		UserAuthorizeApply obj=userService.getUserAuthorizeApplyByID(id);
		model.addAttribute("obj", obj);
		return "/authorize/audit";
	}
	
	@ResponseBody
	@PostMapping("/authorize/audit/save.do")
	public RspResult auditSave(Long id,String status) {
		boolean flag="pass".equals(status);
		userService.auditUserAuthorizeApply(id, flag);
		return new RspResult();
	}
	
	/**
	 * 当前处理进度
	 * */
	@GetMapping("/authorize/process")
	public String process(Long id) {
		String current="handling";
		SessionToken session=this.userService.getSessionUser();
		UserAuthorizeApply obj=userService.getUserAuthorizeApply(session.getUserId());
		if(AuthorizeAuditStaus.pass.equals(obj.getAuditStatus())) {
			current="success";
		}else if(AuthorizeAuditStaus.refuse.equals(obj.getAuditStatus())) {
			current="fail";
		}
		return "/authorize/"+current;
	}

}
