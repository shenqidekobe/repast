package com.yiyou.repast.weixin.controller;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dw.weixin.sdk.openapi.IWeixinBasisAPI;
import com.dw.weixin.sdk.request.oauth.OAuthGetAccessTokenByCodeRequest;
import com.dw.weixin.sdk.request.oauth.OAuthGetCodeRequest;
import com.dw.weixin.sdk.request.oauth.OAuthGetUserInfoRequest;
import com.dw.weixin.sdk.response.oauth.OAuthGetAccessTokenByCodeResponse;
import com.dw.weixin.sdk.response.oauth.OAuthGetUserInfoResponse;
import com.yiyou.repast.merchant.model.User;
import com.yiyou.repast.weixin.base.ThreadContextHolder;
import com.yiyou.repast.weixin.compent.WechatProperties;
import com.yiyou.repast.weixin.service.UserBusinessService;

/**
 * 微信用户授权认证
 */
@Controller
@RequestMapping("/wx/oauth")
public class OAuthController {

	@Resource
	private IWeixinBasisAPI weixinBasisAPI;
	@Resource
	private WechatProperties wechatProperties;
	@Resource
	private UserBusinessService userService;
	
	private final String SESSION_OAUTH_STATE = "session_oauth_state";
	private final String OAUTH_SCOPE="snsapi_base";

	/**
	 * 用户登入进行网页授权
	 */
	@GetMapping()
	public String oauth(HttpServletRequest request,String deskNum) throws Exception {
		String callbackUrl = wechatProperties.getDomain() + request.getContextPath() + "/wx/oauth/callback";
		String sessionState = RandomStringUtils.random(10, true, true);
		request.getSession().setAttribute(SESSION_OAUTH_STATE, sessionState);
		
		OAuthGetCodeRequest oauthReq = new OAuthGetCodeRequest();
		oauthReq.setAppid(wechatProperties.getAppId());
		oauthReq.setScope(OAUTH_SCOPE);// snsapi_base为静默，snsapi_userinfo为用户需确认登录
		oauthReq.setState(sessionState+"_"+deskNum);
		oauthReq.setRedirect_uri(callbackUrl);
		String params="?r=yiyou";
		for(Map.Entry<String, String> entry: oauthReq.getTextParams().entrySet()) {
			params += "&"+entry.getKey()+"="+entry.getValue();
		}
		return "redirect:"+oauthReq.getApiMethodUrl()+params;
	}

	/**
	 * 授权回调结果
	 */
	@GetMapping("/callback")
	public String callback(HttpServletRequest request, String code, String state) throws Exception {
        String sessionState = Objects.toString(request.getSession().getAttribute(SESSION_OAUTH_STATE));
        if(StringUtils.isEmpty(state)||!state.contains("_")) {
        	throw new RuntimeException("非法请求");
        }
        String deskNum=state.split("_")[1];
        String stateCode=state.split("_")[0];
        if(StringUtils.isEmpty(sessionState) || !sessionState.equals(stateCode)){
            throw new RuntimeException("非法请求");
        }
        if(StringUtils.isEmpty(code)){
            throw new RuntimeException("请授权对微信账号的访问权限");
        }
		OAuthGetAccessTokenByCodeRequest tokenReq = new OAuthGetAccessTokenByCodeRequest();
		tokenReq.setCode(code);
		tokenReq.setAppid(wechatProperties.getAppId());
		tokenReq.setSecret(wechatProperties.getAppSecret());
		OAuthGetAccessTokenByCodeResponse rsp = (OAuthGetAccessTokenByCodeResponse) weixinBasisAPI.sendReq(tokenReq,
				null);
		if(rsp.isSuccess()) {
			String openId=rsp.getOpenid();
			User user=new User();
			user.setOpenId(openId);
			user.setMerchantId(ThreadContextHolder.getCurrentMerchantId());
			//授权成功，非静默方式则开始获取用户信息
			if("snsapi_userinfo".equals(OAUTH_SCOPE)) {
				user=getWechatUserInfo(user, rsp.getAccess_token());
			}else {
				int hashCodeV = UUID.randomUUID().toString().hashCode();  
				if(hashCodeV < 0) hashCodeV = - hashCodeV;  
				user.setNickName("吃客"+hashCodeV);//手动设置一个名称
			}
			User obj=this.userService.registerUser(user);
			//开始登录注册到shiro
			UsernamePasswordToken token = new UsernamePasswordToken(obj.getId().toString(),deskNum);
	        SecurityUtils.getSubject().login(token);
        	return "/index";
		}
		return "/fail";
	}
	
	/**
	 * 获取微信用户基本信息注册到数据库
	 * */
	private User getWechatUserInfo(User user,String accessToken) {
		try {
			String openId=user.getOpenId();
			OAuthGetUserInfoRequest req1=new OAuthGetUserInfoRequest();
	    	req1.setOpenid(openId);
	    	req1.setAccess_token(accessToken);
	    	OAuthGetUserInfoResponse rsp1=(OAuthGetUserInfoResponse) this.weixinBasisAPI.sendReq(req1, null);
	    	if(rsp1.isSuccess()){
	    		user=new User();
	    		user.setOpenId(openId);
	    		user.setAvatar(rsp1.getHeadimgurl());
	    		user.setCreateTime(new Date());
	    		user.setNickName(rsp1.getNickname());
	    	}
		} catch (Exception e) {}
		return user;
	}

}
