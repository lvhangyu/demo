package com.forezp.exception;

public class MyException extends Exception {

    private Integer code;

    private String errorMsg;

    public MyException(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public MyException(String message, Integer code, String errorMsg) {
        super(message);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public MyException(String message, Throwable cause, Integer code, String errorMsg) {
        super(message, cause);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public MyException(Throwable cause, Integer code, String errorMsg) {
        super(cause);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String errorMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
