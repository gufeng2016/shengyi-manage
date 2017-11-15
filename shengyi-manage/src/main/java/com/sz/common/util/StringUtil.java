package com.sz.common.util;


/**
 * @author : yunxing.li
 * @date   : 2017年7月18日 下午7:05:44
 * @version: v1.0
 */
public class StringUtil {

  public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
		System.out.println(isNumeric("-200"));
	}
    
    /**
	 * 判断字符串是否为空
	 * @param str
	 * @return true-空  false-不为空
	 */
	public static boolean isNullOrEmpty(String str) {
		if(str == null || "".equals(str) || "null".equals(str)){
			return true;
		}
		return false;
	}
}
