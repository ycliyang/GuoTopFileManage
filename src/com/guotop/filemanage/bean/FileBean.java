package com.guotop.filemanage.bean;

import java.io.File;

import org.json.JSONObject;

import com.guotop.filemanage.util.MyFile;

/**
 *
 *
 *@author: ����
 *@time: 2013-8-12����4:36:00
 */
public class FileBean {
	
	
	
	private String createTime;
	
	private String updateTime;
	
//	private File file;
	
	private boolean directory;
	
	private String path;
	
	private String name;
	
	private int icon;
	
	
	//�����洢��Ŀ¼�ṹ���ݳ�ʼ��
	public FileBean(File file) {
//		this.file=file;
		this.icon = MyFile.getFileIcon(file);
		this.directory=file.isDirectory();
		this.path=file.getPath();
		this.name=file.getName();
	}
	
	//����Ŀ¼�ṹ���ݳ�ʼ��
	public FileBean (JSONObject json){
		try {
			this.directory = json.getBoolean("directory");
			this.path = json.getString("path");
			this.name = json.getString("name");
		} catch (Exception e) {
		}
		this.icon = MyFile.getFileIcon(this);
	}
	
	
	public String getCreateTime() {
		return createTime;
	}
	
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	public String getUpdateTime() {
		return updateTime;
	}
	
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
//	public File getFile() {
//		return file;
//	}
//
//
//	public void setFile(File file) {
//		this.file = file;
//	}


	public int getIcon() {
		return icon;
	}


	public void setIcon(int icon) {
		this.icon = icon;
	}


	public boolean isDirectory() {
		return directory;
	}


	public void setDirectory(boolean directory) {
		this.directory = directory;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
