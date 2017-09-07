package com.yiyou.repast.merchant.compent;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import repast.yiyou.common.exception.BusinessException;
import repast.yiyou.common.exception.ExceptionResponse;
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
	public ExceptionResponse nologinException(BusinessException ex) {
		LoggerUtil.error("----GlobalExceptionHandler nologinException----");
		return ex.getEr();
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponse businessException(BusinessException ex) {
		LoggerUtil.error("----GlobalExceptionHandler businessException----");
		return ex.getEr();
	}
	
	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionResponse handleSQLException(HttpServletRequest request, Exception ex) {
		LoggerUtil.error("----GlobalExceptionHandler handleSQLException----");
		return ExceptionResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
	@ResponseBody
	public ExceptionResponse handleIOException(HttpServletRequest request, Exception ex) {
		LoggerUtil.error("----GlobalExceptionHandler handleIOException----");
		return ExceptionResponse.create(HttpStatus.NOT_FOUND.value(),  ex.getMessage());
	}

	@ExceptionHandler(SignException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponse signException(SignException ex) {
		LoggerUtil.error("----GlobalExceptionHandler signException----");
		return ex.getEr();
	}

}
