package com.guotop.filemanage.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.guotop.filemanage.service.FileManageOpenService;

/**
 * 
 * 
 * @author: 李杨
 * @time: 2013-8-14上午10:43:06
 */
public class MyBootReceiver extends BroadcastReceiver{
	/** 开机广播 **/
	static final String BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

	public void onReceive(Context context, Intent intent) {
		/** 如果为开机广播则开启service **/
		if (intent.getAction().equals(BOOT_COMPLETED)) {
			Intent i = new Intent(context, FileManageOpenService.class);
			context.startService(i);
		}
	}
}
