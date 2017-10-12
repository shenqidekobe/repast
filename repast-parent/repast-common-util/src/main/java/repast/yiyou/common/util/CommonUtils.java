package repast.yiyou.common.util;

import java.util.Date;

public class CommonUtils {

	public static java.util.Date parseDate(String dateStr, String format) {
		java.util.Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			date = (java.util.Date) df.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String format(Date date, String format) {
		if(date==null)return "";
		String str = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			str = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
}
