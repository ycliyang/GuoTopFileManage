package com.guotop.filemanage.util;

import android.content.Context;
import android.os.Message;

/**
 *
 *
 *@author: ����
 *@time: 2013-6-27����3:26:40
 */
public interface TrendsHandlerInterface {
	
	public void resultHandleMessage(Message msg);
	
	public void startHttpThread();
	
	public BaseHandler getHandler();
}
