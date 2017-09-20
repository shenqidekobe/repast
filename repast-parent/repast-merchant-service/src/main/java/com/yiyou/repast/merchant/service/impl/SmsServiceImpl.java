package com.yiyou.repast.merchant.service.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.merchant.dao.SmsRepository;
import com.yiyou.repast.merchant.model.Sms;
import com.yiyou.repast.merchant.service.ISmsService;

import repast.yiyou.common.util.DataGrid;
import repast.yiyou.common.util.LoggerUtil;

@Service
public class SmsServiceImpl implements ISmsService{
	
	@Resource
	private SmsRepository smsRepository;

	@Override
	public Sms findById(Long merchantId, Long id) {
		return null;
	}

	@Override
	public List<Sms> findAll(Long merchantId) {
		return smsRepository.findAll();
	}

	@Override
	public Sms save(Long merchantId, Sms obj) {
		return smsRepository.save(obj);
	}

	@Override
	public void remove(Long merchantId, Long id) {
		smsRepository.delete(id);
	}

	@Override
	public Sms update(Long merchantId, Sms obj) {
		return null;
	}

	@Override
	public DataGrid<Sms> findList(Long merchantId, int page, int pageSize) {
		return null;
	}

	@Override
	public int sendMessage(String mobile, String content) {
		String YuexinUserName = "shfj";// 悦信接口的用户名
		String YuexinUserPwd = "shfj123";// 悦信接口的密码
		String sendMsgUrl = "http://mt.549k.com/send.do";
		String sendMsgRes = getResFromUrl(sendMsgUrl, YuexinUserName,
				YuexinUserPwd, mobile, content);
		LoggerUtil.info("【yuexin】 send sms to  mobile =" + mobile + " message="
				+ content + " sendResult=" + sendMsgRes);
		if (sendMsgRes != null && sendMsgRes.contains("成功"))
			return 0;
		else
			return -1;
	}

	@Override
	public boolean verifyCode(String mobile, String code) {
		Sms sms = this.smsRepository.findSmsByMobileAndContent(mobile, code);
		boolean flag = false;
		// 计算时间在**范围内才合法
		if (sms != null) {
			Long time = sms.getSendDate().getTime();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, -10);// 短信过期时间设置
			Long ct = calendar.getTime().getTime();
			// 当前时间减掉过期时间还小于等于创建时间，则验证，否则直接返回false
			if (ct <= time) {
				flag = true;
			}
		}
		return flag;
	}

	
	private String getResFromUrl(String reqUrl, String sn, String pwd,
			String mobile, String content) {
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(reqUrl);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("Account", sn));
		nvps.add(new BasicNameValuePair("Password", pwd));
		nvps.add(new BasicNameValuePair("Mobile", mobile));
		nvps.add(new BasicNameValuePair("Content", content));
		nvps.add(new BasicNameValuePair("Exno", "0"));


		httpPost.setEntity(new UrlEncodedFormEntity(
				(Iterable<? extends org.apache.http.NameValuePair>) nvps,
				Consts.UTF_8));
		StringBuffer result = new StringBuffer();
		try {
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");

			char[] c = new char[1024];
			int a = isr.read(c);
			while (a != -1) {
				result.append(new String(c, 0, a));
				a = isr.read(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
