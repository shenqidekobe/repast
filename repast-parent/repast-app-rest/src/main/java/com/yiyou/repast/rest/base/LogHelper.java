package com.yiyou.repast.rest.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


public class LogHelper {
	
	public static String print(String...strings){
		List<String> logs = Lists.newArrayList(strings);
		return StringUtils.join(logs, "\n");
	}

	public static String titleOutput(String title) {
		int maxLen = 80;
		List<String> output = new ArrayList<String>();
		output.add("\n" + StringUtils.repeat("-", maxLen));
		output.add("\n" + StringUtils.center(title, maxLen));
		output.add("\n" + StringUtils.repeat("-", maxLen));

		return StringUtils.join(output, "");
	}
}
