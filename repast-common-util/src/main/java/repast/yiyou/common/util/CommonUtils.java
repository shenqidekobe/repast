package repast.yiyou.common.util;

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
	
}
