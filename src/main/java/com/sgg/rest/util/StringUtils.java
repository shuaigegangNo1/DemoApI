package com.sgg.rest.util;

public class StringUtils {
	public static String upperCase( String content) {
		return content.toUpperCase();
	}
	
	public static String trim( String content) {
		return content.trim();
	}
	
	public static boolean isNotBlank(String content) {
		if(content == null ||content.length()<=0 || content.equals("null")) {
			return false;
		}
		return true;
	}
}
