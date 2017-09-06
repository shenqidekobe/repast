package repast.yiyou.common.exception;

/**
 * 自定义业务异常
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -238091758285157331L;

	private Integer errCode;
	private String errMsg;

	private ExceptionResponse er;

	public BusinessException() {
		er = ExceptionResponse.create(errCode, errMsg);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(Integer errCode, String errMsg) {
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