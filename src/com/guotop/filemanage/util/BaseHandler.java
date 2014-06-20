﻿package com.guotop.filemanage.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.guotop.filemanage.F;

/**
 * 
 * 
 * 
 * @author: 李杨
 * @time: 2013-3-18下午4:22:57
 */
public class BaseHandler extends Handler {
	protected Context context;
	protected View view;
	
	public BaseHandler(Context context) {
		this.context = context;
	}
	public BaseHandler(Context context,View view) {
		this.context = context;
		this.view=view;
	}
	
	public BaseHandler(Looper looper) {
		super(looper);
	}
	public HttpThread httpThread;

	public JSONObject getJsonData(Message msg) {
		JSONObject json = null;
		Boolean success;
		Bundle bundle = msg.getData();
		String result = bundle.getString("result");
		boolean flage = false;
		if (result != null) {
			try {
				String message = null;
				json = new MyJSONObject(result);
				success = json.getBoolean("success");
				if (!success) {
					message = json.getString("message");
					if ("timeout".equals(message)) {
//						flage = true;
						
						F.COOKIE=json.getString("sessionId");
						new TrendsHandlerInterface() {
							
							public void startHttpThread() {
								new HttpThread(getHandler()){
									public void run() {
//										result=http.reLogin();
										sendMessage(F.result);
									}
								}.start();
							}
							
							public void resultHandleMessage(Message msg) {
								
							}
							
							public BaseHandler getHandler() {
								return new BaseHandler(context){
									public void handleMessage(Message msg) {
										
										if(F.result==msg.what){
											JSONObject json=getJsonData(msg);
											try {
												if(json.getBoolean("success")){	
													BaseHandler.this.reGet(json);
												}
											} catch (JSONException e) {
												e.printStackTrace();
											}
										}
									}
								};
							}
						}.startHttpThread();
					}else{
						Toast.makeText(context,message, Toast.LENGTH_LONG).show();
					}
				}
			} catch (Exception e) {
				Toast.makeText(context, "系统忙!", Toast.LENGTH_LONG).show();
			}
		}
		if (flage) {
			Toast.makeText(context, "回话权限失败！", Toast.LENGTH_LONG).show();
		}
		try {
			return json==null?new JSONObject("{}"):json;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void reGet(JSONObject json){
		new Thread(){
			public void run() {
				httpThread.run();
			}
		}.start();
	}

	public HttpThread getHttpThread() {
		return httpThread;
	}

	public void setHttpThread(HttpThread httpThread) {
		this.httpThread = httpThread;
	}
}
