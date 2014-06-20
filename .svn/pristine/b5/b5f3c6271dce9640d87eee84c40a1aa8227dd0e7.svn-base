package com.guotop.filemanage.util;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
/*
 * 
 */
public class UploadFileBean {
	private String fileName;// 文件名称
		
	private String formName;// type="file" 表单字段名称
	
	private File file;
	private String filePath;
	private String contentType;
	public UploadFileBean(String filePath){
		this(filePath,"file");
	}
	public UploadFileBean(String filePath, String formName) {
		this.file=new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		this.filePath=filePath;
		int beginIndex = filePath.lastIndexOf(System.getProperty("file.separator"));// The value of file.separator is '\\'
		if(beginIndex < 0) {
			beginIndex = filePath.lastIndexOf("/");
		}
		this.fileName = file.getName();
		this.formName = formName;
		this.contentType = new MimetypesFileTypeMap().getContentType(file);
	}

	
	
	


	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
}
