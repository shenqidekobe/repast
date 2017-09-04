package repast.yiyou.common.exception;

/**
 * 未登录异常
 */
public class NoLoginException extends Exception {

	private static final long serialVersionUID = -238091758285157331L;

	private Integer errCode;
	private String errMsg;

	private ExceptionResponse er;

	public NoLoginException() {
		er = ExceptionResponse.create(errCode, errMsg);
	}

	public NoLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoLoginException(String message) {
		super(message);
	}

	public NoLoginException(Throwable cause) {
		super(cause);
	}

	public NoLoginException(Integer errCode, String errMsg) {
		super(errCode + ":" + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
		er = ExceptionResponse.create(errCode, errMsg);
	}

	public Integer getErrCode() {
		return this.errCode;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

	public ExceptionResponse getEr() {
		return er;
	}
}