package com.yiyou.repast.rest.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import repast.yiyou.common.util.LoggerUtil;

/**
 * 激光推送服务
 * */
public class JpushService {

	public static String APP_KEY = "cd72f6147cfa955f34b927d9";

	public static String MASTER_SECRET = "2d226e8e804110c8a4b0ffa3";

	public static Boolean pushMsg(String alias, String title,Map<String,String> params) {
		Boolean sendRes = false;
		try {
			JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
			PushPayload payload = buildPushObject_alert(alias, title, params);
			PushResult result = jpushClient.sendPush(payload);
			if (result != null && (result.msg_id > 0))
				sendRes = true;
		} catch (Exception e) {
			LoggerUtil.info("Connection error, should retry later:"+e);
		} 
		return sendRes;
	}

	/**
	 * 
	 * @param alias
	 * @param msg
	 * @return
	 */
	public static PushPayload buildPushObject_alert(String alias, String title, Map<String, String> vMap) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(
						StringUtils.isEmpty(alias) ? Audience.all() : Audience
								.alias(alias))
				// .setNotification(Notification.alert(title)) //IOS会无声音
								
				 .setNotification( Notification.newBuilder()
				 .addPlatformNotification( IosNotification.newBuilder()
				  .setAlert(title) .setBadge(1) .setSound("happy.caf")
				  .addExtras(vMap).build()) .addPlatformNotification(
				  AndroidNotification.newBuilder() .setAlert(title)
				  .addExtras(vMap).build()) .build())
				//生产环境
				/*.setOptions(Options.newBuilder().setApnsProduction(true).build())*/
				.build();
	}

	public static void main(String[] args) {
		// 文章
		HashMap<String, String> vMap=new HashMap<String, String>();
		vMap.put("caseId", 4+"");
		JpushService.pushMsg("admin_1", "写给魔都的新年情书",vMap);

	}

}
