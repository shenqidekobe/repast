package repast.yiyou.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 */
public class LoggerUtil {

	private final static Logger log = LoggerFactory.getLogger(LoggerUtil.class);

	/**
	 * 打印警告
	 */
	public static void warn(Object obj) {
		try {
			String location = "";
			StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
			location = stacks[2].getClassName() + "." + stacks[2].getMethodName() + "(" + stacks[2].getLineNumber()
					+ ")";
			if (obj instanceof Exception) {
				Exception e = (Exception) obj;
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw, true));
				String str = sw.toString();
				log.warn(location + str);
			} else {
				log.warn(location + obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印信息
	 */
	public static void info(Object obj) {
		try {
			String location = "";
			StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
			location = stacks[2].getClassName() + "." + stacks[2].getMethodName() + "(" + stacks[2].getLineNumber()
					+ ")";
			if (obj instanceof Exception) {
				Exception e = (Exception) obj;
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw, true));
				String str = sw.toString();
				log.info(location + str);
			} else {
				log.info(location + obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印错误
	 */
	public static void error(Object obj) {
		try {
			String location = "";
			StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
			location = stacks[2].getClassName() + "." + stacks[2].getMethodName() + "(" + stacks[2].getLineNumber()
					+ ")";
			if (obj instanceof Exception) {
				Exception e = (Exception) obj;
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw, true));
				String str = sw.toString();
				log.error(location + str);
			} else {
				log.error(location + obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取调用此函数的代码的位置
	 * 
	 * @return 包名.类名.方法名(行数)
	 */
	public static String getCodeLocation() {
		try {
			String location = "";
			StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
			location = stacks[2].getClassName() + "." + stacks[2].getMethodName() + "(" + stacks[2].getLineNumber()
					+ ")";
			return location;
		} catch (Exception e) {
			LoggerUtil.error(e);
			return "";
		}
	}

}
