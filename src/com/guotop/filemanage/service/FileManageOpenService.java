package com.guotop.filemanage.service;

import com.guotop.filemanage.activity.FileManageActivity;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

/**
 * 
 *
 *
 *@author: 李杨
 *@time: 2013-8-14上午10:47:41
 */
public class FileManageOpenService extends Service {

	public final static String FILEMANAGEOPENSERVICE = "com.guotop.filemanage.FileManageOpenService";

	
	private static FileManageOpenService myself;
	
	
	public static void startInstance(Context context){
		if(myself==null){
			Intent intent = new Intent();
			intent.setClass(context, FileManageOpenService.class);
			context.startService(intent);
		}
	}
	
	private BroadcastReceiver myBroadCast = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(FILEMANAGEOPENSERVICE)) {
				intent.getExtras().getString("name");
				Toast.makeText(context, "接收到了一条广播为" + FILEMANAGEOPENSERVICE,Toast.LENGTH_LONG).show();
				if(intent.getExtras().getString("path")!=null){
//					Intent aIntent=new Intent();
//					aIntent.setClass(context, FileManageActivity.class);
//					aIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					context.startActivity(aIntent);
					
					intent.setClass(context, FileManageActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
				}
				
			}
		}

	};

	@Override
	public void onCreate() {
		myself=this;
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// 注册这两个广播
		IntentFilter myFilter = new IntentFilter();
		myFilter.addAction(FILEMANAGEOPENSERVICE);
		this.registerReceiver(myBroadCast, myFilter);
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}