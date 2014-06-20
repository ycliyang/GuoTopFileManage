package com.guotop.filemanage.util;

import java.util.List;

import org.apache.http.NameValuePair;

import android.app.Notification;

/**
 *
 *
 *@author: ¿Ó—Ó
 *@time: 2013-5-7…œŒÁ11:28:16
 */
public class DowloadFileInformaction {
	private String path;
	private int postting;
	private String fileName;
	private String url;
	private int notificationId;
	private List<NameValuePair> params;
	private Notification notification;
	
	
	
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getPostting() {
		return postting;
	}
	public void setPostting(int postting) {
		this.postting = postting;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUrl() {
		return url;
	}
	
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<NameValuePair> getParams() {
		return params;
	}
	public void setParams(List<NameValuePair> params) {
		this.params = params;
	}
	
	
}
