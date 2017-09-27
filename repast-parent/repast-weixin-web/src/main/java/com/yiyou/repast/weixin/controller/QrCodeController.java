package com.yiyou.repast.weixin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiyou.repast.weixin.service.MerchantBusinessService;

/**
 * 二维码访问中心
 * */
@Controller
@RequestMapping("qrcode")
public class QrCodeController {
	
	@Resource
	private MerchantBusinessService merchantService;
	
	@GetMapping("/{merchantId}/{deskNum}")
	public void base64(@PathVariable Long merchantId,@PathVariable String deskNum,
			HttpServletResponse response)throws Exception {
		 String base64 = merchantService.qrcode(merchantId, deskNum);
         response.setContentType("text/plain");
         response.getOutputStream().print(base64);
	}
	
	@GetMapping("/m/{merchantId}/{deskNum}")
	public void bytes(@PathVariable Long merchantId,@PathVariable String deskNum,
			HttpServletResponse response)throws Exception {
		 byte[] out = merchantService.qrcodeImg(merchantId, deskNum);
		 response.setContentType("image/png");
         response.getOutputStream().write(out);
	}


}
