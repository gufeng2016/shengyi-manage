package com.sz.common.util;

/**
 * Created by Administrator on 2016/1/17.
 */
public class ValidateUtil {
	
    /**
     * 6～16位字符，至少包含数字、大写字母、小写字母、符号中的两种
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        return value.matches("[0-9]+"); 
    }

    /**
     * 6～16位字符，至少包含数字、大写字母、小写字母、符号中的两种
     * @param value
     * @return
     */
    public static boolean checkPwd(String value) {
        return value.matches("/(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,16}$/");
    }

    public static boolean checkEmail(String value, int length) {
        return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
                && value.length() <= length;
    }

    /**
     * 检查电话输入 是否正确
     * 正确格 式 012-87654321、0123-87654321、0123－7654321
     * @param value
     * @return
     */
    public static boolean checkTel(String value) {
        return value.matches("\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)");
    }

    /**
     * 检查手机输入 是否正确
     *
     * @param value
     * @return
     */
    public static boolean checkMobile(String value) {
        return value.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    }

    /**
     * 检查字符串是 否含有HTML标签
     * @param value
     * @return
     */

    public static boolean checkHtmlTag(String value) {
        return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
    }

    /**
     * 检查URL是 否合法
     * @param value
     * @return
     */
    public static boolean checkURL(String value) {
        return value.matches("[a-zA-z]+://[^\\s]*");
    }

    /**
     * 检查IP是否 合法
     * @param value
     * @return
     */
    public static boolean checkIP(String value) {
        return value.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
    }

    /**
     * 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
     * @param value
     * @return
     */

    public static boolean checkQQ(String value) {
        return value.matches("[1-9][0-9]{4,13}");
    }

    /**
     * 检查邮编是否 合法
     * @param value
     * @return
     */
    public static boolean checkPostCode(String value) {
        return value.matches("[1-9]\\d{5}(?!\\d)");
    }

    /**
     * 检查身份证是 否合法,15位或18位
     * @param value
     * @return
     */
    public static boolean checkIDCard(String value) {
        return value.matches("\\d{15}|\\d{18}");
    }

    /**
     * 检查输入是否 超出规定长度
     * Java教程:http://www.javaweb.cc
     * @param length
     * @param value
     * @return
     */
    public static boolean checkLength(String value, int length) {
        return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length;
    }

    /**
     * 检查是否为空 字符串,空：true,不空:false
     *
     * @param value
     * @return
     */
    public static boolean checkNull(String value) {
        return value == null || "".equals(value.trim());
    }

}
