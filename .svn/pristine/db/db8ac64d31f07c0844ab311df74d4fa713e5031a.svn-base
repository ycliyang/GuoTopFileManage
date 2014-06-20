package com.guotop.filemanage.util;

import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 *
 *@author: ¿Ó—Ó
 *@time: 2013-4-10œ¬ŒÁ2:04:21
 */
public class MyJSONObject extends JSONObject{
	
	public MyJSONObject(String str) throws JSONException {
		super(str);
	}
	
	public String getString(String key)throws JSONException {
		String str;
		str = super.getString(key);
		if("null".equals(str)){
			return "";
		}
		return str;
	}
}
