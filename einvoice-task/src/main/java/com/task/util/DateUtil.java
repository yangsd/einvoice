package com.task.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author sdyang
 * @date 2015年12月19日 上午10:51:51
 */
public class DateUtil {

	private static final String formatPattern = "yyyy-MM-dd HH:mm:ss";

	private static final String formatPattern_cn = "yyyy年MM月dd日";

	private static final String formatPattern_short = "yyyy--MM-dd";

	/**
	 * 字符串转换为日期：yyyy-MM-dd
	 * @param str
	 * @return
	 * @author sdyang
	 * @date   2015年12月19日 上午10:54:24
	 */
	public static Date StringToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		if (!str.equals("") && str != null) {
			try {
				return format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 日期转换为字符串（xxxx年xx月xx日）
	 * @param date
	 * @return
	 */
	public static String DateToString_cn(Date date){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern_cn);
		if (date!= null) return format.format(date);
		return null;
	}

	/**
	 * 某月1号
	 * @param month
	 * @return
	 */
	public static Date getFirstDay(int month){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH,1);
		return c.getTime();
	}

	public static String dateToString(Date date,String formatPattern){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		if (date!= null) return format.format(date);
		return null;
	}

	public static String dateStringTransform(String str,String fromfp,String tofp){
		Date date = StringToDate(str,fromfp);
		return dateToString(date,tofp);
	}

	public static Date StringToDate(String str,String fp) {
		SimpleDateFormat format = new SimpleDateFormat(fp);
		if (!str.equals("") && str != null) {
			try {
				return format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
