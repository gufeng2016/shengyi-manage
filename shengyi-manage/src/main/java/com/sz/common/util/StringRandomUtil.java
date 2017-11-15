/**
 */
package com.sz.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author goff.yin 
 * @date 2017-7-18   下午6:46:46 
 * @version 1.0.0 
 */
public class StringRandomUtil {
	
	private static String allChar = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
	/**
	 * @author goff.yin 
	 * @date 2017-7-18 下午6:47:25  
	 * @version 1.0.0 
	 * @param pwds 
	 * @param len
	 * @return
	 */
	public static String getRandomString(List<String> pwds, int len) {
		Map<String, String> pwdMap = new HashMap<String, String>();
		if(pwds != null && pwds.size()>0){
			for (String pwd : pwds) {
				pwdMap.put(pwd, pwd);
			}
		}
		String res = getRandomString(len);
		while(pwdMap.containsKey(res)){
			res = getRandomString(len);
		}
		return res;
	}
	/**
	 * @author goff.yin 
	 * @date 2017-7-18 下午7:09:06  
	 * @version 1.0.0 
	 * @param len
	 */
	private static String getRandomString(int len) {
		String[] allStr = allChar.split(",");
		int lenght = allStr.length-1;
		StringBuffer sb = new StringBuffer("");
		while(sb.length()<len){
			int ar = new Long(Math.round(Math.random()*lenght)).intValue();
			sb.append(allStr[ar]);
		}
		return sb.toString();
	}

}
