package com.yiyou.repast.merchant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yiyou.repast.common.service.IZxingQrService;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RBeanUtils;
import com.yiyou.repast.merchant.base.RspResult;
import com.yiyou.repast.merchant.model.Merchant;
import com.yiyou.repast.merchant.model.MerchantApply;
import com.yiyou.repast.merchant.model.MerchantDesk;
import com.yiyou.repast.merchant.service.IMerchantApplyService;
import com.yiyou.repast.merchant.service.IMerchantDeskService;
import com.yiyou.repast.merchant.service.IMerchantService;

import repast.yiyou.common.util.DataGrid;

/**
 * 商家二维码管理
 */
@Controller
@RequestMapping("/merchant")
public class MerchantQrController {

	@Reference
	private IMerchantDeskService merchantDeskService;
	@Reference
	private IMerchantService merchantService;
	@Reference
	private IMerchantApplyService merchantApplyService;
	@Reference
	private IZxingQrService zxingQrService;

	/**
	 * 商家中心
	 */
	@GetMapping("")
	public String list(Model model) {
		return "/merchant/list";
	}

	@ResponseBody
	@PostMapping("/listData.do")
	public List<Merchant> listData() {
		List<Merchant> list = new ArrayList<>();
		list.add(this.merchantService.find(Constants.MERCHANT_ID));
		return list;
	}

	@GetMapping("/edit")
	public String edit(Model model) {
		model.addAttribute("obj", this.merchantService.find(Constants.MERCHANT_ID));
		return "/merchant/edit";
	}

	@ResponseBody
	@PostMapping("/save.do")
	public RspResult save(Merchant obj) {
		Merchant pojo = this.merchantService.find(Constants.MERCHANT_ID);
		RBeanUtils.copyProperties(obj, pojo);
		merchantService.save(pojo);
		return new RspResult();
	}

	/**
	 * 桌子管理列表
	 */
	@GetMapping("/desk")
	public String deskList(Model model) {
		return "/merchant/deskList";
	}

	@ResponseBody
	@PostMapping("/desk/listData.do")
	public List<MerchantDesk> deskListData(String loginName, String status, String type, Integer page,
			Integer pageSize) {
		page = page == null ? page = 0 : page;
		pageSize = Integer.MAX_VALUE;// 客户端分页，服务端查询所有数据
		DataGrid<MerchantDesk> data = merchantDeskService.findList(Constants.MERCHANT_ID, page, pageSize);
		return data.getRecords();
	}

	@GetMapping("/desk/edit")
	public String deskEdit(Long id, Model model) {
		if (id == null) {
			return "/merchant/addDesk";
		}
		model.addAttribute("obj", this.merchantDeskService.findById(null, id));
		return "/merchant/editDesk";
	}

	@ResponseBody
	@PostMapping("/desk/save.do")
	public RspResult deskSave(MerchantDesk obj) {
		if (obj.getId() == null) {
			merchantDeskService.save(Constants.MERCHANT_ID, obj);
		} else {
			MerchantDesk pojo = merchantDeskService.findById(null, obj.getId());
			RBeanUtils.copyProperties(obj, pojo);
			merchantDeskService.update(Constants.MERCHANT_ID, pojo);
		}
		return new RspResult();
	}

	@ResponseBody
	@PostMapping("/desk/remove.do")
	public RspResult toggle(Long id, String status) {
		merchantDeskService.remove(null, id);
		return new RspResult();
	}

	/**
	 * 下载二维码
	 */
	@GetMapping("/qr")
	public void bytes(String deskNum, HttpServletResponse response)
			throws Exception {
		Long merchantId=Constants.MERCHANT_ID;
		MerchantApply apply = merchantApplyService.findMerchantApplyByMerchantId(merchantId);
		String url = apply.getApplyUrl();
		url = url.concat("?param=" + merchantId);
		String fileName="商家二维码.png";
		if(!StringUtils.isEmpty(deskNum)){
			url = url.concat("_" + deskNum);
			fileName="桌号"+deskNum+"二维码.png";
		}
		Map<String, Object> params = new HashMap<>();
		params.put("data", url);
		
		
		byte[] out = zxingQrService.qrAsByte(params);
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes("utf-8"),
						"iso8859-1"));
		response.setContentType("image/png");
		response.getOutputStream().write(out);
	}

}
