package com.guotop.filemanage.listener;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

/**
 *
 *
 *@author: ����
 *@time: 2013-8-13����12:40:02
 */
public interface BaseItemListener extends OnTouchListener,OnClickListener{
	
	
	void onClickChangeItemBackgroud(View v,boolean flage);
	
}
