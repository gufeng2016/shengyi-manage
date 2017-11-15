/**
 */
package com.sz.exception;

/**
 * 
 * @author goff.yin 
 * @date 2017-8-16   下午4:11:48 
 * @version 1.0.0 
 */
public class MessageException extends Exception{
	
	private String code;

	private String message;

	private Object returnObj;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getReturnObj() {
		return returnObj;
	}

	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}
	public MessageException(String message) {
		this.message = message;
	}
	public MessageException(String code,String message) {
		this.message = message;
		this.code = code;
	}
}
