/**
 */
package com.sz.pojo.request;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @author goff.yin 
 * @date 2017-8-17   下午4:51:08 
 * @version 1.0.0 
 */
public class ResultMessage implements Serializable{
	/**结果代码*/
	private String code;
	/**错误描述*/
	private String message;
	/**结果集*/
	private Map<String, Object> data = Maps.newHashMap();
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
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	/**
	 * 
	 */
	public ResultMessage() {
		
	}
	/**
	 * 
	 */
	public ResultMessage(String msg) {
		this.message = msg;
	}
	public ResultMessage(String code ,String msg) {
		this.message = msg;
		this.code = code;
	}
	/**
	 * @author goff.yin 
	 * @date 2017-8-17 下午4:52:23  
	 * @version 1.0.0 
	 * @return
	 */
	public boolean success() {
		if(this.code.equals("0000")){
			return true;
		}
		return false;
	}
	
	
}
