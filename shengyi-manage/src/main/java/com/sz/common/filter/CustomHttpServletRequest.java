package com.sz.common.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.sz.common.util.XSSHandler;

/**
 * User: wuhaifei
 * Date: 13-8-23
 * Time: 下午08:35
 * HttpServeletRequest 扩展。1.解决GET乱码；2.防御XSS攻击
 */
public class CustomHttpServletRequest extends HttpServletRequestWrapper {
	/**源数据编码格式*/
	private String sourceEncode = "ISO8859-1";
	/**待输出的编码格式*/
	private String targetEncode = "UTF-8";

	public CustomHttpServletRequest(HttpServletRequest request) {
		super(request);
		//sourceEncode = request.getCharacterEncoding();
	}

	public CustomHttpServletRequest(HttpServletRequest request, String targetEncode) {
		super(request);
//        sourceEncode = request.getCharacterEncoding();
//        if(StringUtils.isNotBlank(targetEncode)){
//            this.targetEncode = targetEncode;
//        }
	}


	@Override
	public String[] getParameterValues(String name) {
		String[] value = super.getParameterValues(name);
		return hander(value);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		return convertCharset(value);
	}

	/**
	 * 创建时间： 2013-8-23 下午08:53:53
	 * 创建人：wuhaifei
	 * 参数： 
	 * 返回值： String[]
	 * 方法描述 : 参数处理
	 */
	protected String[] hander(String[] value) {
		if (!ArrayUtils.isEmpty(value)) {
			String[] retVal = new String[value.length];
			for (int i = 0; i < value.length; i++) {
				retVal[i] = hander(value[i]);
			}
			return retVal;
		}
		return value;
	}

	/**
	 * 创建时间： 2013-8-23 下午08:53:53
	 * 创建人：wuhaifei
	 * 参数： 
	 * 返回值： String
	 * 方法描述 : 参数处理
	 */
	protected String hander(String value) {
		if (!StringUtils.isEmpty(value)) {
			// 编码转换
			value = convertCharset(value);
			// xxs防御处理
			value = xxsHandle(value);
		}
		return value;
	}

	/**
	 * 创建时间： 2013-8-23 下午08:53:57
	 * 创建人：wuhaifei
	 * 参数： 
	 * 返回值： String
	 * 方法描述 : 编码转换
	 */
	protected String convertCharset(String value) {
		if (!StringUtils.isEmpty(value)) {
			try {
				if (value.equals(new String(value.getBytes(sourceEncode), sourceEncode))) {    
					return (new String(value.getBytes(sourceEncode), targetEncode));
				 }    
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * 创建时间： 2013-10-17 上午10:25:57
	 * 创建人：wuhaifei
	 * 参数： 
	 * 返回值： String
	 * 方法描述 : xxs防御处理
	 */
	protected String xxsHandle(String value) {
		return XSSHandler.handle(value, true, true, true);
	}


}
