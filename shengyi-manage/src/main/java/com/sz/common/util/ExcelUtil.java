package com.sz.common.util;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.google.common.base.Strings;

/**
 * @author : yunxing.li
 * @date   : 2017年7月20日 下午5:50:00
 * @version: v1.0
 */
public class ExcelUtil {
	  
		/**
		 * 
		 * @param title
		 * @param map
		 * @param data
		 * @param out
		 * @param pattern
		 */
	    public static <T> void exportExcel(String title, List<String> headers, List<String> fileds, List<T> dataList, OutputStream out, String pattern){  
	        //声明一个工作簿  
	        HSSFWorkbook workbook = new HSSFWorkbook();  
	        //生成一个表格  
	        HSSFSheet sheet = workbook.createSheet(title);  
	        //设置表格默认列宽度为15个字符  
	        sheet.setDefaultColumnWidth(15);  
	        //生成一个样式，用来设置标题样式  
	        HSSFCellStyle style = workbook.createCellStyle();  
	        //设置这些样式  
	        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	        //生成一个字体  
	        HSSFFont font = workbook.createFont();  
	        font.setColor(HSSFColor.VIOLET.index);  
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	        //把字体应用到当前的样式  
	        style.setFont(font);  
	        // 生成并设置另一个样式,用于设置内容样式  
	        HSSFCellStyle style2 = workbook.createCellStyle();  
	        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
	        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
	        // 生成另一个字体  
	        HSSFFont font2 = workbook.createFont();  
	        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
	        // 把字体应用到当前的样式  
	        style2.setFont(font2);  
	        //产生表格标题行  
	        HSSFRow row = sheet.createRow(0);  
	        for(int i = 0; i < headers.size(); i++){
	            HSSFCell cell = row.createCell(i);  
	            cell.setCellStyle(style);  
	            HSSFRichTextString text = new HSSFRichTextString(headers.get(i));  
	            cell.setCellValue(text);  
	        }  
	        try {
		        for (int i=0; i < dataList.size(); i++) {  
		            T data = dataList.get(i);
		            row = sheet.createRow(i+1);  
		            for(int j = 0; j < fileds.size(); j++){
		                HSSFCell cell = row.createCell(j);  
		                cell.setCellStyle(style2); 
		            	String fileName = fileds.get(j);
			            String getMethodName = "get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1);  
			            Class tCls = data.getClass();  
	                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});  
	                    Object value = getMethod.invoke(data, new Object[]{}); 
	                    String textValue = null; 
	                    if(value == null) continue;
	                    if (value instanceof Boolean)  
	                    {  
	                        boolean bValue = (Boolean) value;  
	                        textValue = "是";  
	                        if (!bValue)  
	                        {  
	                            textValue = "否";  
	                        }  
	                    }  
	                    else if (value instanceof Date)  
	                    {  
	                        textValue = Strings.isNullOrEmpty(pattern) ? DateUtil.format((Date) value) : DateUtil.format((Date)value, pattern);  
	                    }else {  
	                        // 其它数据类型都当作字符串简单处理  
	                        textValue = value.toString();  
	                    }  
	                    //利用正则表达式判断textValue是否全部由数字组成  
	                    if (textValue != null)  
	                    {  
	                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
	                        Matcher matcher = p.matcher(textValue);  
	                        if (matcher.matches())  
	                        {  
	                            // 是数字当作double处理  
	                            cell.setCellValue(Double.parseDouble(textValue));  
	                        }  
	                        else  
	                        {  
	                            cell.setCellValue(textValue);  
	                        }  
	                    }  
		            }
		        }
		        workbook.write(out);
			} catch (Exception e) {
				e.printStackTrace(); 
			}

	    }
	    
}
