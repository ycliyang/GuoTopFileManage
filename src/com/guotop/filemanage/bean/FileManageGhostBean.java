package com.guotop.filemanage.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.FieldPacker;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.guotop.filemanage.F;
import com.guotop.filemanage.activity.FileManageActivity;
import com.guotop.filemanage.util.BaseHandler;
import com.guotop.filemanage.util.HttpThread;
import com.guotop.filemanage.util.MyFile;

/**
 *
 *
 *@author: 李杨
 *@time: 2013-8-14下午4:56:44
 */
public class FileManageGhostBean {
	
	
	private String reAction;
	
	private boolean lockPath;
	
	private boolean ghost = false;
	
	//读取本地存储卡 true 网络远程false;
	private boolean loca=true;
	
	private String rootPath=MyFile.getSDCardPath();
	
	private List<String> chooseFileList;
	
	private FileManageActivity activity;
	
	private int resultFlage = 1;
	
	private String cookie;
	
	private String readUrl;
	
	private String downloadUrl;
	
	
	/**
	 * 加载网络文件夹时用到
	 */
	BaseHandler handler=new BaseHandler(activity){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == F.result){
				JSONObject jsonObject=this.getJsonData(msg);
				
				try {
					if(jsonObject.getBoolean("success")){
						JSONArray array=jsonObject.getJSONArray("list");
						for (int i=0;i<array.length();i++) {
							activity.getCenterView().getBeans().add(new FileBean(array.getJSONObject(i)));
						}
						activity.getCenterView().showView();
					}else {
						Toast.makeText(context, "读取网络文件列表失败！", Toast.LENGTH_LONG).show();
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else if(F.dowloadFinish==msg.what){
				
				File file = new File(msg.getData().getString("result"));
				if(file.canRead()){
//					MyFile.openFile(activity, file);
					
					Toast.makeText(context, "文件更新成功！", Toast.LENGTH_LONG).show();
				}
			}
		};
	};
	
	
	private boolean serviceObject = false;//服务对象 同一个APK 夸APK
	
	private String cachePath=(MyFile.getSDCardPath()+"/guotopFileManager/cache"); 
	
	public FileManageGhostBean(FileManageActivity fileManageActivity){
		this.activity = fileManageActivity;
		Bundle bundle = activity.getIntent().getExtras();
		if (bundle!=null&&bundle.getString("path") != null) {
			rootPath = activity.getIntent().getStringExtra("path");
			ghost = true;
			if (activity.getIntent().getBooleanExtra("lockPath", false)) {
				activity.getTopView().setRootPath(rootPath);
			}
			
			reAction=bundle.getString("reAction");
			
			serviceObject=bundle.getBoolean("serviceObject", false);
			if(serviceObject){
				resultFlage = bundle.getInt("resultFlage");
			}
			loca=bundle.getBoolean("loca", true);
			
			if(!loca){
				readUrl=bundle.getString("readUrl");
				downloadUrl=bundle.getString("downloadUrl");
				cookie=bundle.getString("cookie");
				cachePath=bundle.getString("cachePath");
			}
			
			activity.getBottomView().getSubmitButton().setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					Intent intent=new Intent();
					
					Object[] pathsString=activity.getCenterView().getChooseFileList().keySet().toArray();
					
					ArrayList<String> pathsList=new ArrayList<String>();
					for (Object obj : pathsString) {
						pathsList.add(obj+"");
					}
					
					//远程调用 通过广播
					if(reAction!=null&&!"".equals(reAction)&&!serviceObject){
						intent = new Intent(reAction);
						intent.putStringArrayListExtra("paths", pathsList);
						activity.sendBroadcast(intent);
					}else if(serviceObject) {
						intent.putStringArrayListExtra("paths", pathsList);
						//本地调用
						activity.setResult(resultFlage,intent);
					}
					activity.finish();
				}
			});
			
			//代码触发模式切换将浏览模式改成编辑模式
			activity.getTopView().getEditModeButton().performClick();
		}
		
	}
	

	
	public void onCheckFile(final FileBean bean){
		if(!isLoca()){//网络加载
			
			
			String pathString=MyFile.getSDCardPath()+cachePath+bean.getPath();
			File file=new File(pathString);
			if(!file.canRead()&&!file.isDirectory()){
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("path",bean.getPath()));
				
				new HttpThread(handler,downloadUrl,params,pathString){
					public void run() {
						bean.setPath(path);
						String [] paths = path.split("/");
						path=path.replace(paths[paths.length-1], "");
						result = http.getFile(downloadUrl,params, path,paths[paths.length-1]);
						sendMessage(F.dowloadFinish);
					};
				}.start();
				Toast.makeText(activity, "文件正在更新请稍等......", Toast.LENGTH_LONG).show();
			}else {
				MyFile.openFile(activity, file);
			}
		}
	}
	
	
	
	public void readNetworkFiles(String path){
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("path",path));
		//读取网络
		new HttpThread(handler,activity.getGhostBean().getReadUrl(),params){
			public void run() {
				F.COOKIE=activity.getGhostBean().getCookie();
				result = http.post(url, params);
				sendMessage(F.result);
			};
		}.start();
	}
	
	public void dowloadNetworkFile(){
		
	}
	
	public String getReAction() {
		return reAction;
	}

	public void setReAction(String reAction) {
		this.reAction = reAction;
	}

	public boolean isLockPath() {
		return lockPath;
	}

	public void setLockPath(boolean lockPath) {
		this.lockPath = lockPath;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public List<String> getChooseFileList() {
		return chooseFileList;
	}

	public void setChooseFileList(List<String> chooseFileList) {
		this.chooseFileList = chooseFileList;
	}


	public boolean isGhost() {
		return ghost;
	}


	public void setGhost(boolean ghost) {
		this.ghost = ghost;
	}



	public boolean isLoca() {
		return loca;
	}



	public void setLoca(boolean loca) {
		this.loca = loca;
	}



	public int getResultFlage() {
		return resultFlage;
	}



	public FileManageActivity getActivity() {
		return activity;
	}



	public void setActivity(FileManageActivity activity) {
		this.activity = activity;
	}



	public String getCookie() {
		return cookie;
	}



	public void setCookie(String cookie) {
		this.cookie = cookie;
	}



	public String getReadUrl() {
		return readUrl;
	}

	public void setReadUrl(String readUrl) {
		this.readUrl = readUrl;
	}


	public String getDownloadUrl() {
		return downloadUrl;
	}



	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}



	public BaseHandler getHandler() {
		return handler;
	}



	public void setHandler(BaseHandler handler) {
		this.handler = handler;
	}



	public void setResultFlage(int resultFlage) {
		this.resultFlage = resultFlage;
	}

	public boolean isServiceObject() {
		return serviceObject;
	}

	public void setServiceObject(boolean serviceObject) {
		this.serviceObject = serviceObject;
	}


	public String getCachePath() {
		return cachePath;
	}



	public void setCachePath(String cachePath) {
		this.cachePath = cachePath;
	}
	
	
	
}
