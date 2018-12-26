package com.hundsun.cprs.commonservice.sms.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 */
public class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	private static final String TIME_PATTERN = "HH:mm";
	private static final String TIME_PATTERN_ALL = "yyyy-MM-dd HH:mm:ss.S";
	private static final String TIME_PATTERN_SS = "yyyy-MM-dd HH:mm:ss";
	// 日期格式 年月日
	public static final String TIME_YYYYMMDD = "yyyyMMdd";

	// 日期格式 时分秒
	public static final String TIME_HHMMSS = "HHmmss";

	// 日期格式时分秒毫秒，注意毫秒的时候多了一位需要特殊处理
	public static final String TIME_HHMMSSSS = "HHmmssSS";

	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	public DateUtil() {
	}

	public static Date convertStringToDate(String aMask, String strDate, boolean lenient) throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);
		df.setLenient(lenient);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * 统计两个时间差，返回的是分钟
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 * @return
	 */
	public static int countMinites(String beginStr, String endStr, String Foramt) {
		Date end = strToDate(endStr, Foramt);
		Date begin = strToDate(beginStr, Foramt);
		long times = end.getTime() - begin.getTime();
		return (int) (times / 60 / 1000);
	}

	public static float countMinites(Date begin, Date end) {
		if (begin == null || end == null)
			return -9999;
		long times = end.getTime() - begin.getTime();
		float days = ((float) times / 60 / 1000);
		return days;
	}

	/**
	 *
	 * 功能：天数向上取整，velocity使用<br>
	 *
	 * @param in
	 * @return
	 * @author shenzh 2011-3-1
	 */
	public static int getWholeDay(String in) {
		Double double1 = Double.parseDouble(in);
		if (double1 < 0) {
			return 0;
		}
		return (int) Math.ceil(double1);
	}

	/**
	 * 根据指定格式判断输入日期是否合法
	 * 
	 * @param str
	 * @param dateFormat
	 * @return isDate("2011-3-18","yyyy-MM-dd")
	 */
	public static boolean isDate(String str, String dateFormat) {
		if (str != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			formatter.setLenient(false);
			try {
				formatter.format(formatter.parse(str));
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
	}

	public static Date formatDatePatternAll(String datestr) {
		if (isEmpty(datestr))
			return null;
		Date dateTemp = null;
		SimpleDateFormat formater2 = new SimpleDateFormat(TIME_PATTERN_ALL);
		try {
			dateTemp = formater2.parse(datestr);
		} catch (Exception e) {
			log.error("exception in convert string to date!");
		}
		return dateTemp;
	}

	public static String getDateElement(Date aDate, String elementType) {
		String element = null;
		String dateStr = DateUtil.convertDateToString(aDate);
		String[] elements = dateStr.split("-");
		for (String ele : elements) {
			System.out.println(ele);
		}
		if ("YYYY".equals(elementType) || "yyyy".equals(elementType)) {
			element = dateStr.substring(0, 4);
		} else if ("MM".equals(elementType) || "mm".equals(elementType)) {
			element = dateStr.substring(5, 7);
		} else if ("DD".equals(elementType) || "dd".equals(elementType)) {
			element = dateStr.substring(8, dateStr.length());
		}
		return element;
	}

	/**
	 * 返回date1-dat2相差的秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int subSecond(Date date1, Date date2) {
		long d1 = date1.getTime();
		long d2 = date2.getTime();
		int sub = (int) ((d1 - d2) / 1000);
		return sub;
	}

	// Timestamp和String之间转换的函数：
	public static String getTimestampToString(Timestamp obj) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 定义格式，不显示毫秒
		String str = df.format(obj);
		return str;
	}

	/*
	 * 自定义 转换模式将Timestamp 输出
	 */
	public static String getTimestampToString(String formatPattern, Timestamp obj) {
		SimpleDateFormat df = new SimpleDateFormat(formatPattern);
		String str = df.format(obj);
		return str;
	}

	/**
	 * String转化为Timestamp:
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp getStringToTimestamp(String str) {
		Timestamp ts = Timestamp.valueOf(str);
		return ts;
	}

	public static Date strToDate(String str, String pattern) {
		Date dateTemp = null;
		SimpleDateFormat formater2 = new SimpleDateFormat(pattern);
		try {
			dateTemp = formater2.parse(str);
		} catch (Exception e) {
			log.error("exception in convert string to date!");
		}
		return dateTemp;
	}

	/**
	 * Return default datePattern (yyyy-MM-dd)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		return "yyyy-MM-dd";
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to yyyy-MM-dd.
	 *
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: yyyy-MM-dd HH:MM
	 * a
	 *
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * This method returns the current date in the format: yyyy-MM-dd
	 *
	 * @return the current date
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	public static Calendar getCurrentDay() throws ParseException {
		Calendar cal = Calendar.getInstance();
		return cal;

	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 *
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based on the
	 * System Property 'dateFormat' in the format you specify on input
	 *
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate
	 *            the date to convert (in format yyyy-MM-dd)
	 * @return a date object
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate + "' to a date, throwing exception");
			log.error(pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return aDate;
	}

	/**
	 *
	 * @param aDate
	 * @return
	 */
	public static String convertDateToString(String pattern, Date aDate) {
		return getDateTime(pattern, aDate);
	}

	/**
	 * 取得从startDate开始的前(负)/后(正)day天
	 * 
	 * @param startDate
	 * @param day
	 * @return
	 */
	public static Date getRelativeDate(Date startDate, int day) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(startDate);
			calendar.add(Calendar.DAY_OF_MONTH, day);
			return calendar.getTime();
		} catch (Exception e) {
			log.error(e);
			return startDate;
		}
	}

	/**
	 * 根据日期获取星期几
	 *
	 * @param date
	 *            java.util.Date对象,不能为null
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 统计两个时间差，返回的是天数(即24小时算一天，少于24小时就为0，用这个的时候最好把小时、分钟等去掉)
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 * @return
	 */
	public static int countDays(String beginStr, String endStr, String Foramt) {
		Date end = strToDate(endStr, Foramt);
		Date begin = strToDate(beginStr, Foramt);
		long times = end.getTime() - begin.getTime();
		return (int) (times / 60 / 60 / 1000 / 24);
	}

	public static float countDays(Date begin, Date end) {
		if (begin == null || end == null)
			return -9999;
		long times = end.getTime() - begin.getTime();
		float days = ((float) times / 60 / 60 / 1000 / 24);
		return days;
	}

	/**
	 * 获取时间格式
	 * 
	 * @param aDate
	 * @param dateformat
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateFormat(Date aDate, String dateformat) {
		if (isEmpty(dateformat)) {
			dateformat = TIME_PATTERN_SS;
		}
		return getDateTime(dateformat, aDate);
	}

	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = false
	 * StringUtil.isEmpty("bob")     = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * </pre>
	 *
	 * @param str
	 *            要检查的字符串
	 *
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	public static String getCurrentTime() {
		return getDate(new Date(), TIME_HHMMSS);
	}

	/**
	 * 获取当前日期，日期格式为yyyyMMdd
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return getDate(new Date(), TIME_YYYYMMDD);
	}

	public static String getDate(Date aDate, String dateFormat) {
		SimpleDateFormat df;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat(dateFormat);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 传入时间类型转换String格式
	 * 
	 * @param date
	 * @return yyyy-MM-dd格式
	 */
	public static String getDateString(Date date) {
		String strdate = "";
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
		strdate = df.format(date);
		return strdate;
	}

}
