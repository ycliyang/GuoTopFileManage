package com.guotop.filemanage.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import com.guotop.filemanage.F;

/**
 * 
 *
 *
 *@author: 李杨
 *@time: 2013-6-17下午1:34:02
 */
public class MyHttp extends Http{

	HttpClient httpClient=null;
	private static final int REQUEST_TIMEOUT = 3*1000;//设置请求超时3秒钟  
	private static final int SO_TIMEOUT = 15*1000;  //设置等待数据超时时间15秒钟  
	   
	{
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
			httpClient = new DefaultHttpClient(httpParams);
	};
	public String SUCCESS = "{success:false,message:\"系统忙！\"}";

	
	

	public String postJson(String url, String json) {
		try {
			return super.postJson(url, json);
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	
	public String post(String url, List<NameValuePair> params) {
		try {
			return super.post(url, params);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	public String post(String url, List<NameValuePair> params, String cookie) {
		try {
			return super.post(url, params, cookie);
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	public String postFile(String url,Map<String,String> paramMap,List<UploadFileBean> files){
		try {
			return super.postFile(url, paramMap, files);
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	public byte[] getFile(String url, List<NameValuePair> params) {
		try {
			return super.getFile(url, params);
		} catch (Exception e) {
			return null;
		}
	}
	public String  getFile(String url,String path,String fileName) {
		try {
			return super.getFile(url, null, path, fileName);
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	

	public String  getFile(String url,List<NameValuePair> params,String path,String fileName) {
		try {
			return super.getFile(url, params, path, fileName);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	public String getFile(String url, List<NameValuePair> params,String path) {
		try {
			return super.getFile(url, params, path, null);
		} catch (Exception e) {
		}
		return SUCCESS;
		
	}
	
	public String  getFile(String url,List<NameValuePair> params,String path,String fileName,DowloadBigFileHttpInterface di) {
		try {
			return super.getFile(url, params, path, fileName, di);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	public String getFile(String url,String path) {
		try {
			return super.getFile(url,null,path,null);
		} catch (Exception e) {
			return null;
		}
	}

	public byte[] getFile(String url) {
		try {
			return super.getFile(url);
		} catch (Exception e) {
			return null;
		}
	}

	public String get(String url) {
		try {
			return super.get(url);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	public String get(String url, String cookie) {
		try {
			return super.get(url, cookie);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	

	public byte[] httpFileByte(String url) {
		try {
			return super.httpFileByte(url);
		} catch (Exception e) {
			return null;
		}
	}

	public byte[] httpFileByte(String url, String cookie) {
		try {
			return super.httpFileByte(url, cookie);
		} catch (Exception e) {
			return null;
		}
		
	}

	public byte[] httpFileByte(String url, List<NameValuePair> params) {
		try {
			return super.getFile(url, params);
		} catch (Exception e) {
			return null;
		}
	}


	

}
