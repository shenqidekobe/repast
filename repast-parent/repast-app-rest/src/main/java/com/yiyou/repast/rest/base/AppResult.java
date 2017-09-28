package com.yiyou.repast.rest.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * APP接口返回对象定义
 * */
@ApiModel(description = "返回接口对象")
public class AppResult {
	
	public final static String SUCCESS="200";//成功
	public final static String ERROR="500";//异常
	public final static String PARAM_NULL="400";//参数为空
	public final static String OBJECT_NULL="404";//对象为空
	public final static String VALID_FIAL="403";//验证失败
	public final static String SIGN_FIAL="444";//签名失败

	@ApiModelProperty(value = "返回代码")
	private String code=SUCCESS;
	@ApiModelProperty(value = "错误消息")
	private String msg;
	@ApiModelProperty(value = "返回JSON")
	private String responseJson;
	
	public AppResult(){}
	public AppResult(String responseJson){
		this.responseJson=responseJson;
	}
	public AppResult(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
}
