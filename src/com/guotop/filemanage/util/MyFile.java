package com.guotop.filemanage.util;

import java.io.File;
import java.util.List;

import com.guotop.filemanage.R;
import com.guotop.filemanage.bean.FileBean;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

/**
 *
 *我的文件读取工具
 *
 *@author: 李杨
 *@time: 2013-8-12下午4:14:44
 */
public class MyFile {
	
	/**
	 * 获取存储卡根目录路径
	 * @return
	 */
	public static String getSDCardPath(){
		File sdDir = null; 
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在 
		if(sdCardExist){                               
			sdDir = Environment.getExternalStorageDirectory();//获取根目录 
		}
		return sdDir.toString(); 
	}
	/**
	 * 获取指定目录下面的所有文件
	 * @param path
	 * @return
	 */
	public static File[] getFileChildrens(String path){
		File file = new File(path);
		//如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!file.exists() || !file.isDirectory()) {  
	        return null;  
	    }
	    return file.listFiles();
	}
	
	
	/**
	 * 根据file 获取目录下面所有文件
	 * @param file
	 * @return
	 */
	public static File[] getFileChildrens(File file){
		//如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!file.exists() || !file.isDirectory()) {  
	        return null;  
	    }
	    return file.listFiles();
	}
	
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	} 
	
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }
	}
	
	
	public static int getFileIcon(File file){
	    if (!file.exists() || !file.isDirectory()) {
	    	String [] name = file.getName().split("\\.");
			String lastName = name[name.length-1];
	        return getIcon(lastName);
	    }
	    return R.drawable.folder_icon;
	}
	
	public static int getFileIcon(FileBean file){
	    if (!file.isDirectory()) {
	    	String [] name = file.getName().split("\\.");
			String lastName = name[name.length-1];
	        return getIcon(lastName);
	    }
	    return R.drawable.folder_icon;
	}
	
	public static int getIcon(String lastName){
		if(lastName.indexOf("ppt")>-1){
			return R.drawable.ppt;
		}else if("doc".indexOf(lastName)>-1){
			return R.drawable.word;
		}else if("txt".indexOf(lastName)>-1){
			return R.drawable.text;
		}else if("pdf".indexOf(lastName)>-1){
			return R.drawable.pdf;
		}else if("jpg".indexOf(lastName)>-1||"png".indexOf(lastName)>-1||"gif".indexOf(lastName)>-1||"jpeg".indexOf(lastName)>-1||"bmp".indexOf(lastName)>-1){
			return R.drawable.photo;
		}else if("excl".indexOf(lastName)>-1){
			return R.drawable.excl;
		}else if("mp3".indexOf(lastName)>-1||"mp4".indexOf(lastName)>-1||"rmvb".indexOf(lastName)>-1||"wmv".indexOf(lastName)>-1||"avi".indexOf(lastName)>-1){
			return R.drawable.media;
		}else if("swf".indexOf(lastName)>-1){
			return R.drawable.swf;
		}else if("avi".indexOf(lastName)>-1){
			return R.drawable.avi;
		}else if("wma".indexOf(lastName)>-1){
			return R.drawable.wma;
		}
		return R.drawable.read;
	}
	
	/**
	 * 打开文件
	 * 
	 * @param file
	 */
	public static void openFile(Context context, File file) {

		// 获取文件file的MIME类型
		String type = getMIMEType(file);
//		if(openFileFilter(context,type,file)){
			Intent intent = new Intent();
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// 设置intent的Action属性
			intent.setAction(Intent.ACTION_VIEW);
			// 设置intent的data和Type属性。
			intent.setDataAndType(/* uri */Uri.fromFile(file), type);
			// 跳转
			context.startActivity(intent);
//		}
	}
	
	public static String getMIMEType(File file) {

		String[][] MIME_MapTable = {
				// {后缀名，MIME类型}
				{ ".3gp", "video/3gpp" },
				{ ".apk", "application/vnd.android.package-archive" },
				{ ".asf", "video/x-ms-asf" },
				{ ".avi", "video/x-msvideo" },
				{ ".bin", "application/octet-stream" },
				{ ".bmp", "image/bmp" },
				{ ".c", "text/plain" },
				{ ".class", "application/octet-stream" },
				{ ".conf", "text/plain" },
				{ ".cpp", "text/plain" },
				{ ".doc", "application/msword" },
				{ ".docx",
						"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
				{ ".xls", "application/vnd.ms-excel" },
				{ ".xlsx",
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
				{ ".exe", "application/octet-stream" },
				{ ".gif", "image/gif" },
				{ ".gtar", "application/x-gtar" },
				// { ".gz", "application/x-gzip" },
				{ ".h", "text/plain" },
				{ ".htm", "text/html" },
				{ ".html", "text/html" },
				{ ".jar", "application/java-archive" },
				{ ".java", "text/plain" },
				{ ".jpeg", "image/jpeg" },
				{ ".jpg", "image/jpeg" },
				{ ".js", "application/x-javascript" },
				{ ".log", "text/plain" },
				{ ".m3u", "audio/x-mpegurl" },
				{ ".m4a", "audio/mp4a-latm" },
				{ ".m4b", "audio/mp4a-latm" },
				{ ".m4p", "audio/mp4a-latm" },
				{ ".m4u", "video/vnd.mpegurl" },
				{ ".m4v", "video/x-m4v" },
				{ ".mov", "video/quicktime" },
				{ ".mp2", "audio/x-mpeg" },
				{ ".mp3", "audio/x-mpeg" },
				{ ".mp4", "video/mp4" },
				{ ".mpc", "application/vnd.mpohun.certificate" },
				{ ".mpe", "video/mpeg" },
				{ ".mpeg", "video/mpeg" },
				{ ".mpg", "video/mpeg" },
				{ ".mpg4", "video/mp4" },
				{ ".mpga", "audio/mpeg" },
				{ ".msg", "application/vnd.ms-outlook" },
				{ ".ogg", "audio/ogg" },
				{ ".pdf", "application/pdf" },
				{ ".png", "image/png" },
				{ ".pps", "application/vnd.ms-powerpoint" },
				{ ".ppt", "application/vnd.ms-powerpoint" },
				{ ".pptx",
						"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
				{ ".prop", "text/plain" }, { ".rc", "text/plain" },
				{ ".rmvb", "audio/x-pn-realaudio" },
				{ ".rtf", "application/rtf" }, { ".sh", "text/plain" },
				{ ".tar", "application/x-tar" },
				{ ".tgz", "application/x-compressed" },
				{ ".txt", "text/plain" }, { ".wav", "audio/x-wav" },
				{ ".wma", "audio/x-ms-wma" }, { ".wmv", "audio/x-ms-wmv" },
				{ ".wps", "application/vnd.ms-works" },
				{ ".xml", "text/plain" }, { ".z", "application/x-compress" },
				{ ".swf", "application/swf" },
				{".apk","application/vnd.android.package-archive"},
				// { ".zip", "application/x-zip-compressed" },
				{ "", "*/*" } };

		String type = "*/*";
		String fName = file.getName();
		// 获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* 获取文件的后缀名 */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++) {
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}
}
