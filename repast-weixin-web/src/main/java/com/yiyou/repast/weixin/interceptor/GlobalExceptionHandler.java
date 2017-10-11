package com.yiyou.repast.weixin.interceptor;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yiyou.repast.weixin.base.RspResult;

import repast.yiyou.common.exception.BusinessException;
import repast.yiyou.common.exception.NoLoginException;
import repast.yiyou.common.exception.SignException;
import repast.yiyou.common.util.LoggerUtil;

/**
 * 异常处理总控制器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(NoLoginException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RspResult nologinException(NoLoginException ex) {
		LoggerUtil.error("----GlobalExceptionHandler nologinException----");
		return new RspResult(ex.getErrCode(), ex.getErrMsg());
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RspResult businessException(BusinessException ex) {
		LoggerUtil.error("----GlobalExceptionHandler businessException----");
		return new RspResult(ex.getErrCode(), ex.getErrMsg());
	}
	
	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public RspResult handleSQLException(HttpServletRequest request, Exception ex) {
		LoggerUtil.error("----GlobalExceptionHandler handleSQLException----");
		return new RspResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
	@ResponseBody
	public RspResult handleIOException(HttpServletRequest request, Exception ex) {
		LoggerUtil.error("----GlobalExceptionHandler handleIOException----");
		return new RspResult(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(SignException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RspResult signException(SignException ex) {
		LoggerUtil.error("----GlobalExceptionHandler signException----");
		return new RspResult(ex.getEr().getCode(), ex.getEr().getMessage());
	}

}
