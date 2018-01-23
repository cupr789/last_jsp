package com.last.jsp.util;

public class URIParser {
	
	public static final int LAST_INDEX_SLASH = 1;
	
	public static String getCommand(String uri,int type) {
		if(type==LAST_INDEX_SLASH) {
			return uri.substring(uri.lastIndexOf("/")+1);
		}
		
		return "";
	}
}
