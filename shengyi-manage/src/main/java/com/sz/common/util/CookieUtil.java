package com.sz.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * 先进行Base64Encode再进行URLEncode
 */
public class CookieUtil {
	
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){
	    Cookie cookie = new Cookie(name, value);
	    cookie.setPath("/");
	    if(maxAge > 0) {
	    	cookie.setMaxAge(maxAge);
	    }
	    response.addCookie(cookie);
	}

}
