package com.sgg.rest.util;

public class BussinessException extends RuntimeException {
	private String code;
	private String msg;
	public BussinessException(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public BussinessException(String string, String message, Exception e) {
		// TODO Auto-generated constructor stub
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
	//HttpCode.CODE_400
//	HttpCode.CODE_500
	public static BussinessException paramException(String message) {
		BussinessException baseExecption = new BussinessException(HttpCode.CODE_400, message);
        return baseExecption;
    }
 
    public static BussinessException serverErrException(String message) {
        return new BussinessException("500", message);
    }
 
    public static BussinessException serverErrException(String message, Exception e) {
        return new BussinessException(HttpCode.CODE_500, message, e);
    }

}
