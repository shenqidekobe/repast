package com.yiyou.repast.weixin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dw.weixin.sdk.pay.WXPay;
import com.dw.weixin.sdk.pay.WXPayConfig;
import com.dw.weixin.sdk.pay.WXPayConfigImpl;
import com.dw.weixin.sdk.pay.WXPayUtil;
import com.yiyou.repast.order.model.Order;
import com.yiyou.repast.pay.model.PaymentRecord;
import com.yiyou.repast.weixin.base.SessionToken;
import com.yiyou.repast.weixin.compent.WechatProperties;
import com.yiyou.repast.weixin.service.OrderBusinessService;
import com.yiyou.repast.weixin.service.UserBusinessService;
import com.yiyou.repast.weixin.service.WechatBusinessService;

import repast.yiyou.common.util.LoggerUtil;

/**
 * 微信支付控制器
 */
@RequestMapping("/wx/pay")
@Controller
public class WechatPayController {

	public static String notify_url = "wx/pay/notify";
	@Resource
	private WechatProperties wechatProperties;
	@Resource
	private WechatBusinessService wechatBusinessService;
	@Resource
	private OrderBusinessService orderBusinessService;
	@Resource
	private UserBusinessService userBusinessService;

	/**
	 * 微信VIP付费咨询支付请求
	 */
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public String pay(Model model, @PathVariable
			Long orderId, HttpServletRequest req) throws Exception {
		SessionToken session = userBusinessService.getSessionUser();
		Order order = orderBusinessService.getOrderById(orderId);
		WXPayConfig config = WXPayConfigImpl.getInstance();
		WXPay wxPay = new WXPay(config, true, false);

		String outTradeNo = session.getMerchantId()+"_"+orderId+"_"+RandomStringUtils.random(6, true, true);// 订单号
		String body = "爱扫码消费订单";
		String totalFee = order.getAmount().multiply(new BigDecimal(100)).intValue()+"";

		Map<String, String> reqData = new HashMap<String, String>();
		reqData.put("openid", session.getOpenId());
		reqData.put("body", body);
		reqData.put("out_trade_no", outTradeNo);
		reqData.put("fee_type", "CNY");
		reqData.put("total_fee", totalFee);
		reqData.put("spbill_create_ip", req.getLocalAddr());
		reqData.put("notify_url", wechatProperties.getDomain()+notify_url);
		reqData.put("trade_type", "JSAPI");
		reqData.put("sign_type", "MD5");
		Map<String, String> rsp = wxPay.unifiedOrder(reqData);

		LoggerUtil.info("微信统一下单支付返回信息：" + rsp);

		String timestamp = String.valueOf(WXPayUtil.getCurrentTimestamp());
		String noncestr = WXPayUtil.generateNonceStr();
		Map<String, String> data = new HashMap<String, String>();
		data.put("appId", config.getAppID());
		data.put("timeStamp", timestamp);
		data.put("nonceStr", noncestr);
		data.put("package", "prepay_id=" + rsp.get("prepay_id"));
		data.put("signType", "MD5");

		String paySign = WXPayUtil.generateSignature(data, config.getKey());
		LoggerUtil.info("微信支付签名信息串：" + data + ";签名结果 = " + paySign);
		model.addAttribute("rsp", rsp);
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("nonceStr", noncestr);
		model.addAttribute("paySign", paySign);
		model.addAttribute("orderId", outTradeNo);
		model.addAttribute("id", orderId);
		return "/pay";
	}

	/**
	 * 支付结果异步通知
	 */
	@RequestMapping("/notify")
	public void payNotify(Model model, HttpServletRequest req, HttpServletResponse response) throws Exception {
		WXPayConfig config = WXPayConfigImpl.getInstance();
		String xmlStr = readStreamParameter(req.getInputStream());
		LoggerUtil.info("微信支付异步通知返回信息：" + xmlStr);
		PrintWriter out = response.getWriter();
		Map<String, String> data = new HashMap<String, String>();

		if (!WXPayUtil.isSignatureValid(xmlStr, config.getKey())) {
			data.put("return_code", "FAIL");
			data.put("return_msg", "SIGN FAIL");
			String xml = WXPayUtil.mapToXml(data);
			out.println(xml);
			return;
		}
		Map<String, String> params = WXPayUtil.xmlToMap(xmlStr);

		String totalFee = params.get("total_fee");
		String outTradeNo = params.get("out_trade_no");
		String transactionId = params.get("transaction_id");// 微信订单号

		totalFee = new BigDecimal(totalFee).divide(new BigDecimal(100)).toString();// 金额除以100
		LoggerUtil.info("...微信通知  订单号：" + outTradeNo + "  交易状态：" + params.get("return_code") + " 金额：" + totalFee
				+ " 验证成功，处理订单 ");
		String[] tradeArr=outTradeNo.split("_");
		Long merchantId= Long.valueOf(tradeArr[0]);
		Long orderId = Long.valueOf(tradeArr[1]);
		PaymentRecord payRecord = wechatBusinessService.findByOrderId(orderId);
		if (payRecord == null) {
			PaymentRecord pr = new PaymentRecord();
			pr.setTrxId(transactionId);
			pr.setAmount(new BigDecimal(totalFee));
			pr.setChannel("wechat");
			pr.setOrderId(orderId);
			pr.setMerchantId(merchantId);
			wechatBusinessService.savePaymentRecord(pr);
		}
		data.put("return_code", "SUCCESS");
		data.put("return_msg", "OK");
		String xml = WXPayUtil.mapToXml(data);
		out.println(xml);
	}

	private static String readStreamParameter(InputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			return new String(buffer.toString().getBytes(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
