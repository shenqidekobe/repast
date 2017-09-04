package repast.yiyou.common.exception;

/**
 * 签名异常
 * */
public class SignException extends Exception {  
  
    private static final long serialVersionUID = 4714113994147018010L;  
    private String message = "AppKey is not correct, please check.";  
    private Integer code = 10002;  
          
    private ExceptionResponse er;  
      
    public SignException() {  
        er = ExceptionResponse.create(code, message);  
    }  
      
    public ExceptionResponse getEr() {  
        return er;  
    }  

}
